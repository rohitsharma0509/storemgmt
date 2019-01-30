package com.app.myproject.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.context.request.WebRequest;

import com.app.myproject.dto.UserDto;
import com.app.myproject.model.User;

public interface UserService {
    User createUser(UserDto userDto);
    
    void update(User user);

    User findByUsername(String username);
    
    Page<User> getUsers(Pageable pageable);

    void updateLocale(HttpServletRequest request, HttpServletResponse response, String language);

    void sendVerificationLink(User user, WebRequest request);
}
