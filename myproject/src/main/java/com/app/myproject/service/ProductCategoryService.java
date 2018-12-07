package com.app.myproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.myproject.model.ProductCategory;

public interface ProductCategoryService {
	
	Long getCategoryIdByName(String name);

	ProductCategory getCategoryById(Long id);

	ProductCategory addCategory(ProductCategory productCategory);

	Page<ProductCategory> getCategories(Pageable pageable);

	List<ProductCategory> getAllCategories();

	void deleteCategory(Long id);

	ProductCategory editCategory(ProductCategory productCategory);
}
