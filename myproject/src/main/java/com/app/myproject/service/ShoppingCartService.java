package com.app.myproject.service;

import javax.servlet.http.HttpServletRequest;

import com.app.myproject.dto.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart getShoppingCart(HttpServletRequest request);
	
	ShoppingCart updateShoppingCart(HttpServletRequest request, ShoppingCart shoppingCart);
	
	void removeShoppingCart(HttpServletRequest request);
}
