package com.wessiq.inventoy_management_system.service;

import com.wessiq.inventoy_management_system.dto.LoginRequest;
import com.wessiq.inventoy_management_system.dto.RegisterRequest;
import com.wessiq.inventoy_management_system.dto.Response;
import com.wessiq.inventoy_management_system.dto.UserDTO;
import com.wessiq.inventoy_management_system.model.User;

public interface UserService {

    Response loginUser(LoginRequest request);

    Response registerUser(RegisterRequest request);

    User getLoggedUser();

    Response getAllUsers();

    Response updateUser(Long id, UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);
}