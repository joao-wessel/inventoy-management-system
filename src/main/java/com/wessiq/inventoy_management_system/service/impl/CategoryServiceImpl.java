package com.wessiq.inventoy_management_system.service.impl;

import com.wessiq.inventoy_management_system.dto.CategoryDTO;
import com.wessiq.inventoy_management_system.dto.Response;
import com.wessiq.inventoy_management_system.exceptions.NotFoundException;
import com.wessiq.inventoy_management_system.model.Category;
import com.wessiq.inventoy_management_system.repository.CategoryRepository;
import com.wessiq.inventoy_management_system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

        categoryRepository.save(category);

        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();
    }

    @Override
    public Response getCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<CategoryDTO> categoryDTOS = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {
        }.getType());

        return Response.builder()
                .status(200)
                .message("Success")
                .categories(categoryDTOS)
                .build();
    }

    @Override
    public Response getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return Response.builder()
                .status(200)
                .message("Success")
                .category(categoryDTO)
                .build();
    }

    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));

        category.setName(categoryDTO.getName());

        categoryRepository.save(category);

        return Response.builder()
                .status(200)
                .message("Category updated successfully")
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));

        categoryRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Category deleted successfully")
                .build();
    }
}