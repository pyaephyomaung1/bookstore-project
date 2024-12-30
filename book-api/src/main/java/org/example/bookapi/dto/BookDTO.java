package org.example.bookapi.dto;

import java.time.LocalDate;

public class BookDTO {
    private int id;
    private String title;
    private double price;
    private LocalDate publicationDate;
    private String description;
    private String bookCoverUrl;
    private String authorName;
    private String categoryName;

    public BookDTO() {}

    public BookDTO(int id,String title, double price, LocalDate publicationDate, String description, String bookCoverUrl, String authorName, String categoryName) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.publicationDate = publicationDate;
        this.description = description;
        this.bookCoverUrl = bookCoverUrl;
        this.authorName = authorName;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
