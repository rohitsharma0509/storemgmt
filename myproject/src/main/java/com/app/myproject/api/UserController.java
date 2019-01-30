package com.app.myproject.api;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.myproject.constants.FieldNames;
import com.app.myproject.constants.RequestUrls;
import com.app.myproject.model.User;
import com.app.myproject.service.OrderService;
import com.app.myproject.service.ProductService;
import com.app.myproject.service.UserService;
import com.app.myproject.util.ChartGenerator;
import com.app.myproject.util.CommonUtil;

@Controller
public class UserController {
	@Inject
	private UserService userService;

	@Inject
	private ProductService productService;
	
	@Inject
	private OrderService orderService;
	
	@Inject
	private ChartGenerator chartGenerator;
	
	@Inject
	private CommonUtil commonUtil;
	
	@Inject
	private Environment environment;

	@GetMapping(value = { "/", RequestUrls.HOME })
	public String home(Model model) {
		Long totalProducts = productService.getNumberOfProducts();
		Long outOfStockProduct = productService.getOutOfStockProductQuantity();
		Long alertProducts = productService.getAlterProductQuantity();
		
		model.addAttribute(FieldNames.TOTAL_PRODUCTS, totalProducts);
		model.addAttribute(FieldNames.ALERT_PRODUCTS, alertProducts);
		model.addAttribute(FieldNames.OUT_OF_STOCK_PRODUCTS, outOfStockProduct);
		model.addAttribute(FieldNames.AVAILABLE_PRODUCTS, totalProducts - outOfStockProduct);
		model.addAttribute(FieldNames.TODAY_ORDER, orderService.countByOrderDateGreaterThanEqual(ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS)));
		model.addAttribute(FieldNames.STOCK_STATUS, Base64.getEncoder().encodeToString(chartGenerator.createPieChart(alertProducts, (totalProducts - outOfStockProduct), outOfStockProduct)));
		model.addAttribute(FieldNames.YEARLY_SALES_GRAPH, Base64.getEncoder().encodeToString(chartGenerator.createLineChart()));
		model.addAttribute(FieldNames.MONTHLY_SALES_GRAPH, Base64.getEncoder().encodeToString(chartGenerator.createBarChart()));
		model.addAttribute("compareGraph", Base64.getEncoder().encodeToString(chartGenerator.createCategoryChart())); 
		return RequestUrls.HOME;
	}

	@GetMapping(value = RequestUrls.USERS)
	public String getUsers(Model model, @PageableDefault(page=1, size=10) Pageable pageable) {
		Page<User> page = userService.getUsers(pageable);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.USERS, page.getNumber()+1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.PAGE, page);
		return RequestUrls.USERS;
	}
	
	@Transactional
	@PostMapping(value = RequestUrls.USERS)
	public String editUser(Model model, @ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) {
		userService.update(user);
		userService.updateLocale(request, response, user.getLanguage());
		return RequestUrls.MY_ACCOUNT;
	}
	
	@GetMapping(value = RequestUrls.PERSONAL_INFO)
	public String getUser(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		java.util.Map<String, String> languages = new java.util.HashMap<>();
		languages.put("en", "English");
		languages.put("hi", "Hindi");
		languages.put("fr", "French");
		model.addAttribute("languages", languages);
		model.addAttribute("user", user);
		return RequestUrls.PERSONAL_INFO;
	}
	
	@GetMapping(value = RequestUrls.MY_ACCOUNT)
	public String myAccount(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return RequestUrls.MY_ACCOUNT;
	}
	
	@GetMapping(value = RequestUrls.ERROR+"/{code}")
	public String showError(Model model, @PathVariable(FieldNames.CODE) Integer code) {
		model.addAttribute(FieldNames.CODE, code);
		model.addAttribute(FieldNames.MESSAGE, environment.getProperty(String.valueOf(code)));
		return RequestUrls.ERROR;
	}
}
