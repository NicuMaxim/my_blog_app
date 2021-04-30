package com.my_blog_app.controllers;

import com.my_blog_app.service.ImageService;
import com.my_blog_app.util_classes.LongTextToShort;
import com.my_blog_app.util_classes.NameByEmail;
import com.my_blog_app.models.Posts;
import com.my_blog_app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

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

    @Autowired
    private LongTextToShort longTextToShort;

    @Autowired
    private ImageService imageService;


    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String fullText,
                              @RequestParam MultipartFile imageFile,
                              Model model) throws IOException {

        String author = nameByEmail.getUserName();
        String shortText = longTextToShort.createShortText(fullText);
        fullText = fullText.replaceAll("\n","<br />");
        byte[] image = imageService.multipartFileToByteArray(imageFile);

        Posts post = new Posts(title, anons, fullText, shortText, author, image);
        postRepository.save(post);

        return "redirect:/blog";
    }



    @GetMapping(value="/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) throws IOException {

        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Posts> post = postRepository.findById(id);
        ArrayList<Posts> res = new ArrayList<>();
        post.ifPresent(res::add);

        String  stringImage = imageService.byteArrayImageToString(post.get().getImage());

        model.addAttribute("image", stringImage);
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
                                  @RequestParam String fullText,
                                  @RequestParam MultipartFile imageFile,
                                  Model model) throws IOException {

        Posts post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);
        post.setShortText(longTextToShort.createShortText(fullText));

        if (!imageFile.isEmpty()) {
            byte[] image = imageService.multipartFileToByteArray(imageFile);
            post.setImage(image);
        }

        postRepository.save(post);

        return "redirect:/blog/{id}";
    }



    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete (@PathVariable (value = "id") long id,
                                  Model model) {

        Posts post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);

        return "redirect:/blog";
    }

}
