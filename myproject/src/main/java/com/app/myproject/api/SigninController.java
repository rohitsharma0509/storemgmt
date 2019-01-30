package com.app.myproject.api;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.model.User;
import com.app.myproject.service.UserService;

@Controller
public class SigninController {
    @Inject
    private UserService userService;
    
    @GetMapping(value = RequestUrls.LOGIN)
    public String login(Model model, String logout) {
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return RequestUrls.LOGIN;
    }
    
    @GetMapping(value = RequestUrls.FORGET_PASSWORD)
    public String forgetPassword(Model model, String username) {
        User user = userService.findByUsername(username);
        return RequestUrls.LOGIN;
    }
}
