package com.example.lms.model;

public class Book {

    private Long id;
    private String name;
    private String edition;
    private String publisher;
    private String language;
    private String category;


    public Book(Long id, String name, String edition, String publisher, String language, String category) {
        this.id = id;
        this.name = name;
        this.edition = edition;
        this.publisher = publisher;
        this.language = language;
        this.category = category;
    }

    public Book(String name, String edition, String publisher, String language, String category) {
        this.id = id;
        this.name = name;
        this.edition = edition;
        this.publisher = publisher;
        this.language = language;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", edition='" + edition + '\'' +
                ", publisher='" + publisher + '\'' +
                ", language='" + language + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
