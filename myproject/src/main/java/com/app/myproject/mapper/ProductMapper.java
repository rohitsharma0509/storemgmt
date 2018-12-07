package com.app.myproject.mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.app.myproject.dto.ProductDto;
import com.app.myproject.dto.jaxb.ProductType;
import com.app.myproject.dto.jaxb.ProductsType;
import com.app.myproject.model.Product;
import com.app.myproject.service.ProductCategoryService;

@Component
public class ProductMapper {
	
	@Inject
	private ProductCategoryService productCategoryService;
	
	public List<ProductDto> productsToProductDtos(List<Product> products) {
		List<ProductDto> productDtos = new ArrayList<>();
		products.stream().filter(Objects::nonNull).forEach(product -> {
			productDtos.add(productToProductDto(product));
		});
		return productDtos;
	}

	public ProductDto productToProductDto(Product product) {
		return productToProductDto(product, false);
	}

	public ProductDto productToProductDto(Product product, boolean isCart) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCode(String.format("PRD%010d", product.getId()));
		productDto.setName(product.getName());
		productDto.setBrandName(product.getBrandName());
		productDto.setCategoryId(product.getCategoryId());
		productDto.setQuantity(isCart ? 1 : product.getQuantity());
		productDto.setAlertQuantity(product.getAlertQuantity());
		productDto.setPurchasePrice(product.getPurchasePrice());
		productDto.setPerProductPrice(product.getPerProductPrice());
		productDto.setBase64Image(Base64.getEncoder().encodeToString(
				product.getImage()));
		return productDto;
	}

	public Product productDtoToProduct(ProductDto productDto) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setCode(productDto.getCode());
		product.setName(productDto.getName());
		product.setBrandName(productDto.getBrandName());
		product.setCategoryId(productDto.getCategoryId());
		product.setQuantity(productDto.getQuantity());
		product.setAlertQuantity(productDto.getAlertQuantity());
		product.setPurchasePrice(productDto.getPurchasePrice());
		product.setPerProductPrice(productDto.getPerProductPrice());
		try {
			product.setImage(null != productDto.getImage() ? productDto
					.getImage().getBytes() : null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return product;
	}

	public List<Product> convertProductsTypeToProducts(ProductsType productsType) {
		if (productsType == null) {
			return null;
		}

		List<Product> products = new ArrayList<>();
		productsType.getProductTypes().stream().filter(Objects::nonNull).forEach(productType -> {
			products.add(convertProductTypeToProduct(productType));
		});
		return products;
	}
	
	public Product convertProductTypeToProduct(ProductType productType) {
		Product product = new Product();
		product.setName(productType.getName());
		product.setCode(productType.getCode());
		product.setBrandName(productType.getBrandName());
		product.setImage("".getBytes());
		product.setCategoryId(productCategoryService.getCategoryIdByName(productType.getCategory()));
		product.setQuantity(Integer.parseInt(productType.getQuantity()));
		product.setAlertQuantity(Integer.parseInt(productType.getAlertQuantity()));
		product.setPurchasePrice(Double.parseDouble(productType.getPurchasePrice()));
		product.setPerProductPrice(Double.parseDouble(productType.getSellPrice()));
		return product;
	}
	
	public List<Product> convertCsvFileToProducts(InputStream is) {
		List<Product> products = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
			String line = null;
			int count = 1;
			while ((line = reader.readLine()) != null) {
				String[] attrs = line.split(",");
				if(count != 1){
					Product product = new Product();
					product.setCategoryId(productCategoryService.getCategoryIdByName(attrs[0]));
					product.setName(attrs[1]);
					product.setCode(attrs[2]);
					product.setBrandName(attrs[3]);
					product.setImage("".getBytes());
					product.setQuantity(Integer.parseInt(attrs[4]));
					product.setAlertQuantity(Integer.parseInt(attrs[5]));
					product.setPurchasePrice(Double.parseDouble(attrs[6]));
					product.setPerProductPrice(Double.parseDouble(attrs[7]));
					products.add(product);
				}
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}
}
