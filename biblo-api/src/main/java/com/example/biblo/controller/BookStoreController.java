package com.example.biblo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblo.dto.AuthorDTO;
import com.example.biblo.dto.CategoryDTO;
import com.example.biblo.dto.BookDTO;
import com.example.biblo.service.BookStoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/bookstore")
@RequiredArgsConstructor
public class BookStoreController {
    private final BookStoreService bookStoreService;

    @GetMapping("/authors")
    public List<AuthorDTO> getAuthors() {
        return bookStoreService.getAuthors();
    }

    @GetMapping("/books")
    public List<BookDTO> getBooks() {
        return bookStoreService.getBooks();
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return bookStoreService.getCategories();
    }
}
