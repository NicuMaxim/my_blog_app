package com.my_blog_app.repository;

import com.my_blog_app.models.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Posts, Long> {
}
