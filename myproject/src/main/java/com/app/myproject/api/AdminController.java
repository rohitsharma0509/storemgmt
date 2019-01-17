package com.app.myproject.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.myproject.constants.RequestUrls;

@Controller
public class AdminController {

    @GetMapping(value = RequestUrls.ADMIN)
    public String registration(Model model) {
        return "admin";
    }
}
