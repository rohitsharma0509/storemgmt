package com.app.myproject.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.myproject.model.ProductCategory;
import com.app.myproject.repository.ProductCategoryRepository;
import com.app.myproject.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Inject
	private ProductCategoryRepository productCategoryRepository;
	
	@Override
	public Long getCategoryIdByName(String name) {
		return productCategoryRepository.findByName(name).getId();
	}

	@Override
	public ProductCategory getCategoryById(Long id) {
		return productCategoryRepository.findById(id).get();
	}

	@Override
	public ProductCategory addCategory(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

	@Override
	public Page<ProductCategory> getCategories(Pageable pageable) {
		PageRequest request = PageRequest.of(pageable.getPageNumber() - 1,
				pageable.getPageSize(), pageable.getSort());
		return productCategoryRepository.findAll(request);
	}

	@Override
	public List<ProductCategory> getAllCategories() {
		return productCategoryRepository.findAll();
	}

	@Override
	public void deleteCategory(Long id) {
		productCategoryRepository.deleteById(id);
	}

	@Override
	public ProductCategory editCategory(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}
}