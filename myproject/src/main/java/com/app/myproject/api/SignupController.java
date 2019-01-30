package com.app.myproject.api;

import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.dto.UserDto;
import com.app.myproject.model.User;
import com.app.myproject.model.UserToken;
import com.app.myproject.service.UserService;
import com.app.myproject.service.UserTokenService;
import com.app.myproject.validator.UserValidator;

@Controller
public class SignupController {
    @Inject
    private UserService userService;

    @Inject
    private UserValidator userValidator;
    
    @Inject
    private UserTokenService userTokenService;
    
    @GetMapping(value = RequestUrls.REGISTRATION)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDto());
        return RequestUrls.REGISTRATION;
    }

    @PostMapping(value = RequestUrls.REGISTRATION)
    public String registration(@ModelAttribute("userForm") UserDto userDto, BindingResult bindingResult, Model model, WebRequest request) {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return RequestUrls.REGISTRATION;
        }
        User user = userService.createUser(userDto);
        userService.sendVerificationLink(user, request);
        model.addAttribute("message", "Acount activation link has been sent to your email.");
        return RequestUrls.LOGIN;
    }
    
    @GetMapping(value = RequestUrls.REGISTRATION_CONFIRM)
    public String confirmRegistration(WebRequest request, Model model, @RequestParam String token) {
        UserToken userToken = userTokenService.getUserToken(token);
        
        if (userToken == null) {
            model.addAttribute("message", "Invalid token");
            return RequestUrls.LOGIN;
        }
         
        User user = userToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((userToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", "Acount activation link has been expired.");
            return RequestUrls.LOGIN;
        } 
        user.setIsEnabled(true);
        userService.update(user);
        model.addAttribute("message", "Your acount has been activated now.");
        return RequestUrls.LOGIN; 
    }
}
