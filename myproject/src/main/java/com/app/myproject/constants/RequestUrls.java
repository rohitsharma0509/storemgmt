package com.app.myproject.constants;

public class RequestUrls {
	private RequestUrls() {
	}
	public static final String PRODUCT_ALL = "/products/all";
	public static final String PRODUCTS_AJAX = "/products/ajax";
	public static final String ADD_PRODUCT = "/admin/addProduct";
	public static final String PRODUCTS = "/admin/products";
	public static final String DELETE_PRODUCT = "/admin/products/{id}";
	public static final String PRODUCTS_IMPORT = "/admin/products/import";
	public static final String PRODUCTS_SAVE = "/admin/products/save";
	public static final String PRODUCTS_IMPORT_FILE = "/admin/importProducts";
	
	public static final String BUY = "/buy";
	public static final String GET_ORDERS = "/orders/{id}";
	public static final String ORDERS = "/orders";
	public static final String DOWNLOAD_ORDER = "/orders/download/{id}";
	
	public static final String STOCK = "/stock";
	
	public static final String ADMIN = "/admin";
	
	public static final String CUSTOMERS = "/customers";
	
	public static final String COMPOSE_EMAIL = "/composeEmail";
	public static final String SEND_EMAIL = "/sendEmail";
	public static final String GET_EMAIL_ACCOUNT = "/getEmailAccount";
	public static final String ADD_EMAIL_ACCOUNT = "/addEmailAccount";
	public static final String EMAIL_TEMPLATES = "/admin/emailTemplates";
	
	public static final String EXCEL = "/excel";
	public static final String SAMPLE = "/sample";
	
	public static final String ADD_CATEGORY = "/admin/addCategory";	
	public static final String CATEGORIES = "/admin/categories";	
	public static final String CATEGORIES_ALL = "/admin/categories/all";	
	public static final String DELETE_CATEGORY = "/admin/categories/{id}";
	
	public static final String ADD_TO_CART = "/addToCart";
	public static final String DELETE_FROM_CART = "/shoppingCart/{id}";
	public static final String SHOPPING_CART = "/shoppingCart";
	public static final String CHECKOUT = "/checkout";
	
	public static final String ADD_SUPPLIER = "/addSupplier";
	public static final String SUPPLIERS = "/suppliers";
	public static final String SUPPLIERS_ALL = "/suppliers/all";
	public static final String DELETE_SUPPLIER = "/suppliers/{id}";
	
	public static final String REGISTRATION = "/registration";
	public static final String LOGIN = "/login";
	public static final String HOME = "/home";
	public static final String ACCESS_DENIED = "/accessDenied";
	public static final String USERS = "/admin/users";
	public static final String ERROR = "/error";
	
	public static final String ROLES = "/admin/roles";
	public static final String ADD_ROLE = "/admin/addRole";
	public static final String CHANGE_LANGUAGE = "/changeLanguage";
	public static final String CHANGE_LOCALE = "/changeLocale";
	public static final String PERSONAL_INFO = "/personalInfo";
	public static final String MY_ACCOUNT = "/myAccount";
	
	public static final String PROFIT_LOSS = "/profitLoss";
	public static final String DAILY_PROFIT_LOSS = "/dailyProfitLoss";
	public static final String MONTHLY_PROFIT_LOSS = "/monthlyProfitLoss";
	public static final String QUARTERLY_PROFIT_LOSS = "/quarterlyProfitLoss";
	public static final String YEARLY_PROFIT_LOSS = "/yearlyProfitLoss";
}
