package com.app.myproject.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.app.myproject.constants.RequestUrls;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, 
      HttpServletResponse response, AuthenticationException exception) throws IOException {
        String errorMessage = "";
        if(exception instanceof DisabledException) {
            errorMessage = "User is disabled";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "Your username or password is invalid.";
        }
        request.getSession().setAttribute("error", errorMessage);
        response.sendRedirect(RequestUrls.LOGIN);
    }
}
