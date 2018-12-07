package com.app.myproject.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.myproject.constants.RequestUrls;

@Controller
public class AdminController {
	@RequestMapping(value = RequestUrls.ADMIN, method = RequestMethod.GET)
    public String registration(Model model) {
        return "admin";
    }
}
