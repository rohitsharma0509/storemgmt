package com.app.myproject.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.handler.CustomAuthenticationFailureHandler;
import com.app.myproject.handler.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Inject
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Inject
	private CustomAuthenticationFailureHandler authenticationFailureHandler;
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers(RequestUrls.ADMIN, RequestUrls.ADMIN+"/*").access("hasAuthority('ADMIN')").
		antMatchers(RequestUrls.REGISTRATION, RequestUrls.REGISTRATION_CONFIRM).permitAll().
		antMatchers("/resources/**").permitAll().anyRequest().authenticated()
		.and().formLogin().loginPage(RequestUrls.LOGIN).permitAll()
		.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
		.and().exceptionHandling().accessDeniedPage(RequestUrls.ERROR+"/"+HttpStatus.FORBIDDEN.value())
		.and().logout().invalidateHttpSession(true).deleteCookies("rememberMe").permitAll()
		.and().rememberMe().tokenValiditySeconds(5);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}