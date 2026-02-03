package com.wessiq.inventoy_management_system.service;

import com.wessiq.inventoy_management_system.dto.CategoryDTO;
import com.wessiq.inventoy_management_system.dto.Response;

public interface CategoryService {

    Response createCategory(CategoryDTO categoryDTO);

    Response getCategories();

    Response getCategoryById(Long id);

    Response updateCategory(Long id, CategoryDTO categoryDTO);

    Response deleteCategory(Long id);
}