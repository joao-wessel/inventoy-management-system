package com.wessiq.inventoy_management_system.service.impl;

import com.wessiq.inventoy_management_system.dto.CategoryDTO;
import com.wessiq.inventoy_management_system.dto.Response;
import com.wessiq.inventoy_management_system.dto.SupplierDTO;
import com.wessiq.inventoy_management_system.exceptions.NotFoundException;
import com.wessiq.inventoy_management_system.model.Category;
import com.wessiq.inventoy_management_system.model.Supplier;
import com.wessiq.inventoy_management_system.repository.CategoryRepository;
import com.wessiq.inventoy_management_system.repository.SupplierRepository;
import com.wessiq.inventoy_management_system.service.CategoryService;
import com.wessiq.inventoy_management_system.service.SupplierService;
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
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        supplierRepository.save(supplier);

        return Response.builder()
                .status(200)
                .message("Supplier created successfully")
                .build();
    }

    @Override
    public Response getSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<SupplierDTO> supplierDTOS = modelMapper.map(suppliers, new TypeToken<List<SupplierDTO>>() {
        }.getType());

        return Response.builder()
                .status(200)
                .message("Success")
                .suppliers(supplierDTOS)
                .build();
    }

    @Override
    public Response getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));

        SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);

        return Response.builder()
                .status(200)
                .message("Success")
                .supplier(supplierDTO)
                .build();
    }

    @Override
    public Response updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));

        supplier.setName(supplierDTO.name());

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