package com.app.myproject.events;

import javax.inject.Inject;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.service.UserTokenService;
import com.app.myproject.util.EmailUtil;

@Component
public class RegistrationCompleteListener implements ApplicationListener<RegistrationCompleteEvent> {
    
    @Inject
    private EmailUtil emailUtil;
    
    @Inject
    private UserTokenService verificationTokenService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        System.out.println("Listening event......");
        String token = verificationTokenService.createUserToken(event.getUser());
        String confirmationUrl = "http://localhost:8080"+event.getUrl() + RequestUrls.REGISTRATION_CONFIRM+"?token=" + token;
        emailUtil.sendEmail(new String[] {event.getUser().getEmail()}, null, null, "Registration Confirmation", confirmationUrl);
    }
}
