package com.my_blog_app.models;
import javax.persistence.*;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String anons;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String fullText;

    private String shortText;

    private String author;

    @Lob
    private byte[] image;

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

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getAuthor() {
        return author;
    }

    public String getShortText() { return shortText; }

    public void setShortText(String shortText) { this.shortText = shortText; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Posts() {
    }

    public Posts(String title, String anons, String fullText, String shortText, String author, byte[] image) {

        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
        this.shortText = shortText;
        this.author = author;
        this.image = image;

    }

}
