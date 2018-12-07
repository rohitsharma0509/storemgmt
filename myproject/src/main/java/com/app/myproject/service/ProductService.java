package com.app.myproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.ProductDto;
import com.app.myproject.dto.StockDto;
import com.app.myproject.model.Product;

public interface ProductService {
	
	ProductDto getProductByIdForCart(Long id);

	ProductDto getProductById(Long id);

	Product addProduct(ProductDto product);

	Page<Product> getProducts(Pageable pageable);
	
	CustomPage<Product> searchProducts(Pageable pageable, Map<String, String> params);
	
	CustomPage<ProductDto> searchProductDtos(Pageable pageable, Map<String, String> params);

	List<ProductDto> getAllProducts();

	void deleteProduct(Long id);

	Product editProduct(Product product);
	
	Long getNumberOfProducts();

	CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params, Boolean isExcel);
	
	CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params);
	
	Long getAlterProductQuantity();
	
	Long getOutOfStockProductQuantity();

	void importProducts(MultipartFile multiPartFile, String fileType);
	
	Integer getAvailableQuantity(Long id);
}
