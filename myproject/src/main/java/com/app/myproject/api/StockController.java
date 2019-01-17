package com.app.myproject.api;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.StockDto;
import com.app.myproject.service.ProductCategoryService;
import com.app.myproject.service.ProductService;
import com.app.myproject.util.CommonUtil;

@Controller
public class StockController {
	
	@Inject
	private ProductService productService;
	
	@Inject
	private ProductCategoryService productCategoryService;
	
	@Inject
	private CommonUtil commonUtil;
	
	@GetMapping(value = RequestUrls.STOCK)
	public String getStock(Model model,
			@RequestParam(required = false) String categoryId,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String productName,
			@PageableDefault(page = 1, size = 10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("brandName", brandName);
		params.put("productName", productName);
		CustomPage<StockDto> page = productService.getStockDetails(pageable, params);
		model.addAttribute("page", page);
		model.addAttribute("categories", productCategoryService.getAllCategories());
		model.addAttribute("pagging", commonUtil.getPagging("stock", page.getPageNumber()+1, page.getTotalPages(), params));
		return "stock";
	}
}
