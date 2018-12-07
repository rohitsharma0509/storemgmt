package com.app.myproject.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.app.myproject.dto.ShoppingCart;
import com.app.myproject.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public ShoppingCart getShoppingCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		
		if(shoppingCart == null){
			session.setAttribute("shoppingCart", new ShoppingCart());
		}
		return (ShoppingCart) session.getAttribute("shoppingCart");
	}

	@Override
	public ShoppingCart updateShoppingCart(HttpServletRequest request, ShoppingCart shoppingCart) {
		HttpSession session = request.getSession();
		session.setAttribute("shoppingCart", shoppingCart);
		return shoppingCart;
	}
	
	public void removeShoppingCart(HttpServletRequest request){
		request.getSession().removeAttribute("shoppingCart");
	}
}
