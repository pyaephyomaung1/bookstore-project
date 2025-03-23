package com.example.biblo.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private int id;
    private String title;
    private double price;
    private String publishDate;
    private String description;
    private String bookCover;
    private String authorName;
    private Set<String> categories;
}
