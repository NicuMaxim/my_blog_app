package com.my_blog_app.service;

import com.my_blog_app.models.User;
import com.my_blog_app.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
