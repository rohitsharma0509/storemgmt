package com.app.myproject.exceptions;

import javax.inject.Inject;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.myproject.constants.RequestUrls;

@ControllerAdvice
@PropertySource("classpath:errorMessages.properties")
public class CustomExceptionHandler {
	
	@Inject
	private Environment environment;
	
	@ExceptionHandler(Exception.class)
	public String processException(Model model, Exception exception) {
		model.addAttribute("message", environment.getProperty(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())));		
		return "redirect:"+RequestUrls.ERROR+"/"+HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
