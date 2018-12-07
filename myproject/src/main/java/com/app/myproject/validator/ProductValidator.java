package com.app.myproject.validator;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.myproject.dto.ProductDto;
import com.app.myproject.util.CommonUtil;

@Component
public class ProductValidator implements Validator {

	@Inject
	private CommonUtil commonUtil;

	@Override
	public boolean supports(Class<?> aClass) {
		return ProductDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ProductDto productDto = (ProductDto) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brandName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "perProductPrice", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchasePrice", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alertQuantity", "NotEmpty");

		if (!commonUtil.isValid(productDto.getCategoryId() + "")) {
			errors.rejectValue("categoryId", "Select.Category");
		}

		if (!commonUtil.isDouble(productDto.getPerProductPrice() + "")) {
			errors.rejectValue("perProductPrice", "Invalid.Sell.Price");
		}

		if (!commonUtil.isDouble(productDto.getPurchasePrice() + "")) {
			errors.rejectValue("purchasePrice", "Invalid.Purchase.Price");
		}

		if (!commonUtil.isInteger(productDto.getQuantity() + "")) {
			errors.rejectValue("quantity", "Invalid.Quantity");
		}

		if (!commonUtil.isInteger(productDto.getAlertQuantity() + "")) {
			errors.rejectValue("alertQuantity", "Invalid.Alert.Quantity");
		}
	}

}
