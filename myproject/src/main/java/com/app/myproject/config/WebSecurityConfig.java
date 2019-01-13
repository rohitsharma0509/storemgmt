package com.app.myproject.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.handler.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Inject
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

	@Bean
	@Override
	public AuthenticationManager authenticationManager() {
		return (Authentication authentication) -> {
			String name = authentication.getName();
			String pass = authentication.getCredentials().toString();
			UserDetails user = userDetailsService.loadUserByUsername(name);
			if (user.getUsername().equals(name) && bCryptPasswordEncoder().matches(pass,user.getPassword())) {
				return new UsernamePasswordAuthenticationToken(name, pass, user.getAuthorities());
			} else {
				return null;
			}
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers("/admin", "/admin/*").access("hasAuthority('ADMIN')").
		antMatchers("/resources/**", "/registration").permitAll().anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll().successHandler(authenticationSuccessHandler)
		.and().exceptionHandling().accessDeniedPage(RequestUrls.ERROR+"/"+HttpStatus.FORBIDDEN.value())
		.and().logout().invalidateHttpSession(true).deleteCookies("rememberMe").permitAll()
		.and().rememberMe().tokenValiditySeconds(5);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder( bCryptPasswordEncoder());
	}
}