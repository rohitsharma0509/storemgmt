package com.app.myproject.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.myproject.model.Role;

@Component
public class RoleValidator  implements Validator {
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Role.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
	}
}
