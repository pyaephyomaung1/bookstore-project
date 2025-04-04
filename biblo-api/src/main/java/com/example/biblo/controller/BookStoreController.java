package com.example.biblo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/authors/store")
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        return bookStoreService.createAuthor(authorDTO);
    }

    @PostMapping("/books/store")
    public BookDTO createBook(BookDTO bookDTO) {
        return bookStoreService.createBook(bookDTO);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        bookStoreService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
