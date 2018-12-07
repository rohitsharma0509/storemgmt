package com.app.myproject.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.dto.CustomerDto;
import com.app.myproject.dto.ProductDto;
import com.app.myproject.dto.ShoppingCart;
import com.app.myproject.service.ProductService;
import com.app.myproject.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	@Inject
	private ShoppingCartService shoppingCartService;
	
	@Inject
	private ProductService productService;
	
	@RequestMapping(value = RequestUrls.ADD_TO_CART, method = RequestMethod.GET)
	public String addToCart(Model model, HttpServletRequest request, @RequestParam(value = "id", required=true) Long id) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request);
		Optional<ProductDto> optionalProductDto = shoppingCart.getProductDtos().stream().filter(productDto -> productDto.getId()==id).findFirst();
		Integer availableQuantity = productService.getAvailableQuantity(id);
		if(optionalProductDto.isPresent()){
			if(availableQuantity > optionalProductDto.get().getQuantity()) {
				optionalProductDto.get().setQuantity(optionalProductDto.get().getQuantity()+1);
			}
			optionalProductDto.get().setAvailableQuantity(availableQuantity);
		}else{
			ProductDto productDto = productService.getProductByIdForCart(id);
			productDto.setAvailableQuantity(availableQuantity);
			shoppingCart.getProductDtos().add(productDto);
		}
		
		shoppingCart.setTotalPrice(0.0);
		if(!CollectionUtils.isEmpty(shoppingCart.getProductDtos())){
			for(ProductDto productDto : shoppingCart.getProductDtos()){
				shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + (productDto.getPerProductPrice()*productDto.getQuantity()));
			}
		}
		
		model.addAttribute("shoppingCart", shoppingCart);
		return "redirect:shoppingCart";
	}
	
	@RequestMapping(value = RequestUrls.DELETE_FROM_CART, method = RequestMethod.DELETE)
	public String removeFromCart(Model model, HttpServletRequest request, @PathVariable(value = "id") Long id) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request);
		
		ProductDto productDtoToDelete = null;
		if(shoppingCart != null && !CollectionUtils.isEmpty(shoppingCart.getProductDtos())){
			for(ProductDto productDto : shoppingCart.getProductDtos()){
				if(id == productDto.getId()){
					productDtoToDelete = productDto;
				}
			}
			if(null != productDtoToDelete){
				shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()-productDtoToDelete.getPerProductPrice());
				shoppingCart.getProductDtos().remove(productDtoToDelete);
				
			}
		}
		model.addAttribute("shoppingCart", shoppingCart);
		return "shoppingCart";
	}
	
	@RequestMapping(value = RequestUrls.SHOPPING_CART, method = RequestMethod.GET)
	public String getShoppingCart(Model model, HttpServletRequest request) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request);
		model.addAttribute("shoppingCart", shoppingCart);
		return "shoppingCart";
	}
	
	@RequestMapping(value = RequestUrls.CHECKOUT, method = RequestMethod.GET)
	public String checkout(Model model, HttpServletRequest request, @RequestParam(value = "id", required=false) Long id) {
		if(null == id){
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request);
			model.addAttribute("productDtos", shoppingCart.getProductDtos());
			model.addAttribute("totalPrice", shoppingCart.getTotalPrice());
		}else {
			List<ProductDto> productDtos = new ArrayList<>();
			ProductDto productDto = productService.getProductByIdForCart(id);
			productDtos.add(productDto);
			model.addAttribute("productDtos", productDtos);
			model.addAttribute("totalPrice", productDto.getPerProductPrice());
		}
		model.addAttribute("customerDto", new CustomerDto());
		return "shoppingCartConfirm";
	}
	
	@RequestMapping(value = RequestUrls.SHOPPING_CART,method = RequestMethod.POST)
	public String updateShoppingCart(Model model, @ModelAttribute ShoppingCart shoppingCart, HttpServletRequest request) {
		model.addAttribute("shoppingCart", shoppingCartService.updateShoppingCart(request, shoppingCart));
		return "redirect:checkout";
	}
}