package com.my_blog_app.repository;

import com.my_blog_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
         User findByEmailIgnoreCase(String email);
}
