package com.app.myproject.api;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = RequestUrls.COMPOSE_EMAIL, method = RequestMethod.GET)
	public String composeEmail(Model model) {
		model.addAttribute("email", new Email());
		return "composeEmail";
	}
	
	@RequestMapping(value = RequestUrls.SEND_EMAIL, method = RequestMethod.POST)
	public String sendEmail(Model model, @ModelAttribute("email")  @Valid Email email, BindingResult bindingResult) {
		emailValidator.validate(email, bindingResult);
		if (bindingResult.hasErrors()) {
			return "composeEmail";
		}
		emailService.sendEmail(email);
		return "admin";
	}
	
	@RequestMapping(value = RequestUrls.GET_EMAIL_ACCOUNT, method = RequestMethod.GET)
	public String getEmailAccount(Model model, @RequestParam(value = "id", required=false) Long id) {
		model.addAttribute("emailAccount", emailService.getEmailAccount());
		return "addEmailAccount";
	}
	
	@RequestMapping(value = RequestUrls.ADD_EMAIL_ACCOUNT, method = RequestMethod.POST)
	public String addEmailAccount(@ModelAttribute("emailAccount")  @Valid EmailAccount emailAccount, BindingResult bindingResult) {
		emailService.addEmailAccount(emailAccount);
		return "redirect:/getEmailAccount";
	}
	
	@RequestMapping(value = RequestUrls.EMAIL_TEMPLATES, method = RequestMethod.GET)
	public String getEmailTemplates() {
		return "admin/emailTemplates";
	}
}