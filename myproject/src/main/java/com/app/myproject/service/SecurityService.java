package com.app.myproject.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
