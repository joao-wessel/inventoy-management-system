package com.wessiq.inventoy_management_system.service;

import com.wessiq.inventoy_management_system.dto.Response;
import com.wessiq.inventoy_management_system.dto.SupplierDTO;

public interface SupplierService {

    Response createSupplier(SupplierDTO supplierDTO);

    Response getSuppliers();

    Response getSupplierById(Long id);

    Response updateSupplier(Long id, SupplierDTO supplierDTO);

    Response deleteSupplier(Long id);
}