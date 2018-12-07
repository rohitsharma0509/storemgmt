package com.app.myproject.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.myproject.model.Role;
import com.app.myproject.model.User;
import com.app.myproject.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
    	System.out.println("loadUserByUsername");
        User user = userRepository.findByUsername(username);

        if(null != user){
        	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        	for (Role role : user.getRoles()){
        		grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        	}
        	
        	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }else {
        	throw new UsernameNotFoundException("Username not found");
        }
    }
}
