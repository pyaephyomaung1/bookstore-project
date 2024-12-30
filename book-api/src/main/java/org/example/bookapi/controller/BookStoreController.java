package org.example.bookapi.controller;

import org.example.bookapi.dto.AuthorDTO;
import org.example.bookapi.dto.BookDTO;
import org.example.bookapi.service.BookStoreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookstore")
@CrossOrigin("*")
public class BookStoreController {
    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }


    @GetMapping("/books")
    public List<BookDTO> getAllBooks() {
        return bookStoreService.getAllBooks();
    }

    @GetMapping("/authors")
    public List<AuthorDTO> getAllAuthors() {
        return bookStoreService.getAllAuthors();
    }

    @GetMapping("/new_arrivals")
    public List<BookDTO> getNewArrival(){
        return bookStoreService.getNewArrivals();
    }

    @GetMapping("/best_seller")
    public List<BookDTO> getBestSeller(){
        return bookStoreService.getBestSellers();
    }



    @GetMapping("/authors/{author_code}")
    public Optional<AuthorDTO> getAuthorByAuthorCode(@PathVariable("author_code") String authorCode) {
        System.out.println("Fetching author for code: " + authorCode);
        return bookStoreService.getAuthorByAuthorCode(authorCode);
    }


    @GetMapping("/books/search/{title}")
    public Optional<BookDTO> getBookByTitle(@PathVariable String title) {
        return bookStoreService.getBookByTitle(title);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/books/{id}")
    public Optional<BookDTO> getBookByID(@PathVariable int id) {
        return bookStoreService.getBookByID(id);
    }

}
