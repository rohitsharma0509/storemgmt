package com.app.myproject.api;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.myproject.constants.FieldNames;
import com.app.myproject.constants.RequestUrls;
import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.ProductDto;
import com.app.myproject.model.Product;
import com.app.myproject.service.ProductCategoryService;
import com.app.myproject.service.ProductService;
import com.app.myproject.util.CommonUtil;
import com.app.myproject.validator.ProductValidator;

@Controller
public class ProductController {

	@Inject
	private ProductService productService;
	
	@Inject
	private ProductCategoryService productCategoryService;
	
	@Inject
	private CommonUtil commonUtil;
	
	@Inject
	private ProductValidator productValidator;
	
	@RequestMapping(value = RequestUrls.ADD_PRODUCT, method = RequestMethod.GET)
	public String addProduct(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		ProductDto productDto;
		if(id != null){
			productDto = productService.getProductById(id);
		}else {
			productDto = new ProductDto();
		}
		model.addAttribute(FieldNames.PRODUCTDTO, productDto);
		model.addAttribute(FieldNames.CATEGORIES, productCategoryService.getAllCategories());
		return RequestUrls.ADD_PRODUCT;
	}
	
	@RequestMapping(value = RequestUrls.PRODUCTS, method = RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute(FieldNames.PRODUCTDTO)  @Valid ProductDto productDto, BindingResult bindingResult) {
		productValidator.validate(productDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute(FieldNames.CATEGORIES, productCategoryService.getAllCategories());
			return RequestUrls.ADD_PRODUCT;
		}
		
		productService.addProduct(productDto);
		return "redirect:"+RequestUrls.PRODUCTS;
	}
	
	@RequestMapping(value =RequestUrls.PRODUCTS, method = RequestMethod.GET)
	public String getProducts(Model model, 
			@RequestParam(required = false) String categoryId,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String productName,
			@RequestParam(required = false) String statusId,
			@PageableDefault(page=1, size=10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.CATEGORY_ID, categoryId);
		params.put(FieldNames.BRAND_NAME, brandName);
		params.put(FieldNames.PRODUCT_NAME, productName);
		params.put(FieldNames.STATUS_ID, statusId);
		CustomPage<Product> page = productService.searchProducts(pageable, params);
		java.util.Map<String, String> statuses = new java.util.HashMap<>();
		statuses.put("1", "Alert");
		statuses.put("2", "Available");
		statuses.put("3", "Out Of Stock");
		model.addAttribute("statuses", statuses);
		model.addAttribute(FieldNames.CATEGORIES, productCategoryService.getAllCategories());
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.PRODUCTS, page.getPageNumber()+1, page.getTotalPages(), null));
	    model.addAttribute(FieldNames.PAGE, page);
		return RequestUrls.PRODUCTS;
	}
	
	@RequestMapping(value = RequestUrls.DELETE_PRODUCT, method = RequestMethod.DELETE)
	public String deleteProduct(Model model, @PathVariable(FieldNames.ID) Long id) {
		productService.deleteProduct(id);
		return RequestUrls.PRODUCTS;
	}
	
	@RequestMapping(value = RequestUrls.PRODUCTS, method = RequestMethod.PUT)
	public String editProduct(Model model, @ModelAttribute(FieldNames.PRODUCT) Product product) {
		productService.editProduct(product);
		return RequestUrls.PRODUCTS;
	}
	
	@RequestMapping(value = RequestUrls.PRODUCTS_IMPORT, method = RequestMethod.GET)
	public String importProducts() {
		return RequestUrls.PRODUCTS_IMPORT_FILE;
	}
	
	@RequestMapping(value = RequestUrls.PRODUCTS_SAVE, method = RequestMethod.POST)
	public String importProductsFromFile(@RequestParam(FieldNames.FILE) MultipartFile multiPartFile, @RequestParam(required= true) String fileType) {
		productService.importProducts(multiPartFile, fileType);
		return RequestUrls.PRODUCTS_IMPORT_FILE;
	}
	
	//Non-admin API
	@RequestMapping(value = RequestUrls.PRODUCT_ALL, method = RequestMethod.GET)
	public String getAllProducts(Model model,
			@RequestParam(required = false) String categoryId,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String productName,
			@RequestParam(required = false) String price,
			@PageableDefault(page=1, size=12) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.CATEGORY_ID, categoryId);
		params.put(FieldNames.BRAND_NAME, brandName);
		params.put(FieldNames.PRODUCT_NAME, productName);
		CustomPage<ProductDto> page = productService.searchProductDtos(pageable, params);
		model.addAttribute(FieldNames.CATEGORIES, productCategoryService.getAllCategories());
		model.addAttribute(FieldNames.PAGE, page);
		return "allProducts";
	}
	
	@RequestMapping(value = RequestUrls.PRODUCTS_AJAX, method = RequestMethod.GET)
	public String loadProducts(Model model, @RequestParam(required = false) String categoryId,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String productName,
			@RequestParam(required = false) String price,
			@PageableDefault(page=1, size=12) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.CATEGORY_ID, categoryId);
		params.put(FieldNames.BRAND_NAME, brandName);
		params.put(FieldNames.PRODUCT_NAME, productName);
		CustomPage<ProductDto> page = productService.searchProductDtos(pageable, params);
		model.addAttribute(FieldNames.PAGE, page);
		return "productListing";
	}
}