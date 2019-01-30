package com.app.myproject.api;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.dto.Email;
import com.app.myproject.model.EmailAccount;
import com.app.myproject.service.EmailService;
import com.app.myproject.validator.EmailValidator;

@Controller
public class EmailController {
	
	@Inject
	private EmailService emailService;
	
	@Inject
	private EmailValidator emailValidator;
	
	@GetMapping(value = RequestUrls.COMPOSE_EMAIL)
	public String composeEmail(Model model) {
		model.addAttribute("email", new Email());
		return "composeEmail";
	}
	
	@PostMapping(value = RequestUrls.SEND_EMAIL)
	public String sendEmail(Model model, @ModelAttribute("email")  @Valid Email email, BindingResult bindingResult) {
		emailValidator.validate(email, bindingResult);
		if (bindingResult.hasErrors()) {
			return "composeEmail";
		}
		emailService.sendEmail(email);
		return "admin";
	}
	
	@GetMapping(value = RequestUrls.GET_EMAIL_ACCOUNT)
	public String getEmailAccount(Model model, @RequestParam(value = "id", required=false) Long id) {
		model.addAttribute("emailAccount", emailService.getEmailAccount());
		return "addEmailAccount";
	}
	
	@PostMapping(value = RequestUrls.ADD_EMAIL_ACCOUNT)
	public String addEmailAccount(@ModelAttribute("emailAccount")  @Valid EmailAccount emailAccount, BindingResult bindingResult) {
		emailService.addEmailAccount(emailAccount);
		return "redirect:/getEmailAccount";
	}
}