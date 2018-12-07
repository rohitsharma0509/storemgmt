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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.myproject.constants.FieldNames;
import com.app.myproject.constants.RequestUrls;
import com.app.myproject.model.User;
import com.app.myproject.service.OrderService;
import com.app.myproject.service.ProductService;
import com.app.myproject.service.SecurityService;
import com.app.myproject.service.UserService;
import com.app.myproject.util.ChartGenerator;
import com.app.myproject.util.CommonUtil;
import com.app.myproject.validator.UserValidator;

@Controller
public class UserController {
	@Inject
	private UserService userService;

	@Inject
	private SecurityService securityService;

	@Inject
	private UserValidator userValidator;

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

	@RequestMapping(value = RequestUrls.REGISTRATION, method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return RequestUrls.REGISTRATION;
	}

	@RequestMapping(value = RequestUrls.REGISTRATION, method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return RequestUrls.REGISTRATION;
		}
		userService.save(userForm);
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		return "redirect:"+RequestUrls.HOME;
	}

	@RequestMapping(value = RequestUrls.LOGIN, method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Your username or password is invalid.");
		}else if (logout != null) {
			model.addAttribute("message", "You have been logged out successfully.");
		}
		return RequestUrls.LOGIN;
	}

	@RequestMapping(value = { "/", RequestUrls.HOME }, method = RequestMethod.GET)
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
		return RequestUrls.HOME;
	}

	@RequestMapping(value = RequestUrls.USERS, method = RequestMethod.GET)
	public String getUsers(Model model, @PageableDefault(page=1, size=10) Pageable pageable) {
		Page<User> page = userService.getUsers(pageable);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.USERS, page.getNumber()+1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.PAGE, page);
		return RequestUrls.USERS;
	}
	
	@Transactional
	@RequestMapping(value = RequestUrls.USERS, method = RequestMethod.POST)
	public String editUser(Model model, @ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) {
		userService.update(user);
		userService.updateLocale(request, response, user.getLanguage());
		return RequestUrls.MY_ACCOUNT;
	}
	
	@RequestMapping(value = RequestUrls.PERSONAL_INFO, method = RequestMethod.GET)
	public String getUser(Model model) {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(username);
		java.util.Map<String, String> languages = new java.util.HashMap<>();
		languages.put("en", "English");
		languages.put("hi", "Hindi");
		languages.put("fr", "French");
		model.addAttribute("languages", languages);
		model.addAttribute("user", user);
		return RequestUrls.PERSONAL_INFO;
	}
	
	@RequestMapping(value = RequestUrls.MY_ACCOUNT, method = RequestMethod.GET)
	public String myAccount(Model model) {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return RequestUrls.MY_ACCOUNT;
	}
	
	@RequestMapping(value = RequestUrls.ERROR+"/{code}", method = RequestMethod.GET)
	public String showError(Model model, @PathVariable(FieldNames.CODE) Integer code) {
		model.addAttribute(FieldNames.CODE, code);
		model.addAttribute(FieldNames.MESSAGE, environment.getProperty(String.valueOf(code)));
		return RequestUrls.ERROR;
	}
}
