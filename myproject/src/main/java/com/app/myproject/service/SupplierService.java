package com.app.myproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.myproject.model.Supplier;

public interface SupplierService {
	List<Supplier> getAllSuppliers();

	Supplier addSupplier(Supplier supplier);

	void deleteSupplier(Long id);

	Page<Supplier> getSuppliers(Pageable pageable);

	Supplier editSupplier(Supplier supplier);

	Supplier getSupplierById(Long id);
}
