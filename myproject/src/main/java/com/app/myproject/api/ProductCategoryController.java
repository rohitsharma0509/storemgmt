package com.app.myproject.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
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

import com.app.myproject.constants.FieldNames;
import com.app.myproject.constants.RequestUrls;
import com.app.myproject.model.ProductCategory;
import com.app.myproject.service.ProductCategoryService;
import com.app.myproject.util.CommonUtil;
import com.app.myproject.validator.ProductCategoryValidator;

@Controller
public class ProductCategoryController {

	@Inject
	private ProductCategoryService productCategoryService;
	
	@Inject
	private CommonUtil commonUtil;
	
	@Inject
	private ProductCategoryValidator productCategoryValidator;
	
	@RequestMapping(value = RequestUrls.ADD_CATEGORY,method = RequestMethod.GET)
	public String addCategory(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		ProductCategory productCategory;
		if(id != null){
			productCategory = productCategoryService.getCategoryById(id);
		}else {
			productCategory = new ProductCategory();
		}
		model.addAttribute(FieldNames.PRODUCT_CATEGORY, productCategory);
		return RequestUrls.ADD_CATEGORY;
	}
	
	@RequestMapping(value = RequestUrls.CATEGORIES, method = RequestMethod.POST)
	public String addCategory(Model model, @Valid ProductCategory productCategory, BindingResult bindingResult) {
		productCategoryValidator.validate(productCategory, bindingResult);
		if (bindingResult.hasErrors()) {
			return RequestUrls.ADD_CATEGORY;
		}
		
		productCategoryService.addCategory(productCategory);
		return "redirect:"+RequestUrls.CATEGORIES;
	}
	
	@RequestMapping(value = RequestUrls.CATEGORIES, method = RequestMethod.GET)
	public String getCategories(Model model, @PageableDefault(page=1, size=10) Pageable pageable) {
		Page<ProductCategory> page = productCategoryService.getCategories(pageable);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.CATEGORIES, page.getNumber()+1, page.getTotalPages(), null));
	    model.addAttribute(FieldNames.PAGE, page);
		return RequestUrls.CATEGORIES;
	}
	
	@RequestMapping(value = RequestUrls.CATEGORIES_ALL,method = RequestMethod.GET)
	public String getAllCategories(Model model) {
		List<ProductCategory> categories = productCategoryService.getAllCategories();
		model.addAttribute(FieldNames.CATEGORIES, categories);
		return RequestUrls.CATEGORIES;
	}
	
	@RequestMapping(value = RequestUrls.DELETE_CATEGORY, method = RequestMethod.DELETE)
	public String deleteCategory(Model model, @PathVariable(FieldNames.ID) Long id) {
		productCategoryService.deleteCategory(id);
		return RequestUrls.CATEGORIES;
	}
	
	@RequestMapping(value = RequestUrls.CATEGORIES, method = RequestMethod.PUT)
	public String editCategory(Model model, @ModelAttribute(FieldNames.PRODUCT_CATEGORY) ProductCategory productCategory) {
		productCategoryService.editCategory(productCategory);
		return RequestUrls.CATEGORIES;
	}
}