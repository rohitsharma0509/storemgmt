package com.app.myproject.mapper;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.app.myproject.dto.UserDto;
import com.app.myproject.model.User;

@Component
public class UserMapper {
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setLanguage(StringUtils.isEmpty(userDto.getLanguage()) ? "en" : userDto.getLanguage());
        user.setMobile(userDto.getMobile());
        user.setAddressLine1(userDto.getAddressLine1());
        user.setAddressLine2(userDto.getAddressLine2());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setPincode(userDto.getPincode());
        user.setCountry(userDto.getCountry());
        user.setIsEnabled(false);
        user.setRoles(new HashSet<>());
        return user;
    }
}
