package com.my_blog_app.repository;

import com.my_blog_app.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
