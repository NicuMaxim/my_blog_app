package com.my_blog_app.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String anons;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String full_text;

    private String short_text;

    private String author;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public String getAuthor() {
        return author;
    }

    public String getShort_text() { return short_text; }

    public void setShort_text(String short_text) { this.short_text = short_text; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Posts() {
    }

    public Posts(String title, String anons, String full_text, String short_text, String author) {

        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.short_text = short_text;
        this.author = author;

    }

}
