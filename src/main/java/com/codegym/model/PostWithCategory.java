package com.codegym.model;

public class PostWithCategory {

    private String name;
    private String author;
    private String content;
    private String categoryName;

    public PostWithCategory(String name, String author, String content, String categoryName) {
        this.name = name;
        this.author = author;
        this.content = content;
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
