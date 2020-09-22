package com.my_blog_app.controllers;

import com.my_blog_app.NameByEmail;
import com.my_blog_app.models.Posts;
import com.my_blog_app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@ComponentScan
@Controller
public class BlogController {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Posts> postsIterator = postRepository.findAll();
        List<Posts> posts = new ArrayList<>();
        postsIterator.forEach(posts::add);

        model.addAttribute("posts", posts);

        return "blog-main";
    }


    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }


    @Autowired
    private NameByEmail nameByEmail;


    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model) {

        String author = nameByEmail.getUserName();

        Posts post = new Posts(title, anons, full_text, author);
        postRepository.save(post);

        return "redirect:/blog";
    }



    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {

        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Posts> post = postRepository.findById(id);
        ArrayList<Posts> res = new ArrayList<>();
        post.ifPresent(res::add);

        model.addAttribute("post", res);
        return "blog-details";
    }




    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {

        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Posts> post = postRepository.findById(id);
        ArrayList<Posts> res = new ArrayList<>();
        post.ifPresent(res::add);

        model.addAttribute("post", res);
        return "blog-edit";
    }



    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate (@PathVariable (value = "id") long id,
                                  @RequestParam String title,
                                  @RequestParam String anons,
                                  @RequestParam String full_text,
                                  Model model) {

        Posts post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);

        postRepository.save(post);

        return "redirect:/blog";
    }



    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete (@PathVariable (value = "id") long id,
                                  Model model) {

        Posts post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);

        return "redirect:/blog";
    }

}
