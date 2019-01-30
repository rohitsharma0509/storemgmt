package com.app.myproject.events;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.app.myproject.model.User;

public class RegistrationCompleteEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private String url;

    private Locale locale;

    private User user;

    public RegistrationCompleteEvent(User user, Locale locale, String url) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.url = url;
        System.out.println("Event has been published.");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
