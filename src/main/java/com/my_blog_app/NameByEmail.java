package com.my_blog_app;

import com.my_blog_app.models.User;
import com.my_blog_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NameByEmail {

    public NameByEmail() {
    }

    @Autowired
    private UserRepository userRepository;


    public String getUserName() {


        Authentication current_user = SecurityContextHolder.getContext().getAuthentication();
        String current_email = current_user.getName();

        Iterable<User> postsIterator = userRepository.findAll();

        List<User> users = new ArrayList<>();
        postsIterator.forEach(users::add);

        String firstName = null;
        String lastName = null;

        for (User user : users) {
            if (user.getEmail().equals(current_email)) {

                firstName = user.getFirstName();
                lastName = user.getLastName();
            }
        }

        String author = firstName + " " + lastName;

        return author;
    }
}
