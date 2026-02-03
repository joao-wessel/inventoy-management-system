package com.wessiq.inventoy_management_system.service.impl;

import com.wessiq.inventoy_management_system.dto.LoginRequest;
import com.wessiq.inventoy_management_system.dto.RegisterRequest;
import com.wessiq.inventoy_management_system.dto.Response;
import com.wessiq.inventoy_management_system.dto.UserDTO;
import com.wessiq.inventoy_management_system.enums.UserRole;
import com.wessiq.inventoy_management_system.exceptions.InvalidCredentialsException;
import com.wessiq.inventoy_management_system.exceptions.NotFoundException;
import com.wessiq.inventoy_management_system.model.User;
import com.wessiq.inventoy_management_system.repository.UserRepository;
import com.wessiq.inventoy_management_system.security.JwtUtils;
import com.wessiq.inventoy_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    @Override
    public Response loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return Response.builder()
                .status(200)
                .message("User logged in successfully")
                .role(user.getRole())
                .token(token)
                .expiration_time("1 day")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public Response registerUser(RegisterRequest request) {
        UserRole userRole = UserRole.MANAGER;

        if (request.role() != null) {
            userRole = request.role();
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phone(request.phone())
                .role(userRole)
                .build();

        userRepository.save(user);

        return Response.builder()
                .status(200)
                .message("User created successfully")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));

        user.setTransactions(null);

        return user;
    }

    @Override
    @Transactional
    public Response getAllUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        users.forEach(user -> user.setTransactions(null));

        List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>() {
        }.getType());

        userDTOS.forEach(userDTO -> userDTO.setTransactions(null));

        return Response.builder()
                .status(200)
                .message("Success")
                .users(userDTOS)
                .build();
    }

    @Override
    public Response updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }

        if (userDTO.getPhone_number() != null) {
            user.setPhone(userDTO.getPhone_number());
        }

        if (user.getRole() != null) {
            user.setRole(userDTO.getRole());
        }

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(user);

        return Response.builder()
                .status(200)
                .message("User successfully updated")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public Response deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("User deleted successfully")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public Response getUserTransactions(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        userDTO.getTransactions().forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setSupplier(null);
        });

        return Response.builder()
                .status(200)
                .message("Success")
                .user(userDTO)
                .build();
    }
}