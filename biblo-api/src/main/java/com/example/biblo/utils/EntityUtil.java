package com.example.biblo.utils;



import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.biblo.dto.AuthorDTO;
import com.example.biblo.dto.BookDTO;
import com.example.biblo.dto.CategoryDTO;
import com.example.biblo.entity.Author;
import com.example.biblo.entity.Book;
import com.example.biblo.entity.Category;


public class EntityUtil {

    public static BookDTO toBookDTO(Book book) {
        String authorName = book.getAuthor().getName();
        Set<String> categories = book.getCategories().stream().map(category -> category.getName()).collect(Collectors.toSet());
        String publishDate = book.getPublishDate().toString();
        return new BookDTO(
            book.getId(),
            book.getTitle(),
            book.getPrice(),
            publishDate,
            book.getDescription(),
            book.getBookCover(),
            authorName,
            categories
        );
    }

    public static Book toBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setPublishDate(LocalDate.parse(bookDTO.getPublishDate()));
        book.setDescription(bookDTO.getDescription());
        book.setBookCover(bookDTO.getBookCover());
        return book;
    }

    public static AuthorDTO toAuthorDTO(Author author) {
        List<BookDTO> books = author.getBooks().stream().map(EntityUtil::toBookDTO).collect(Collectors.toList());
        String birthDate = author.getBirthDate().toString();
        return new AuthorDTO(
            author.getId(),
            author.getName(),
            author.getNationality(),
            birthDate,
            author.getBiography(),
            author.getAuthorProfile(),
            books
        );
    }

    public static Author toAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setNationality(authorDTO.getNationality());
        author.setBirthDate(LocalDate.parse(authorDTO.getBirthDate()));
        author.setBiography(authorDTO.getBiography());
        author.setAuthorProfile(authorDTO.getAuthorProfile());
        return author;
    }

    public static CategoryDTO toCategoryDTO(Category category) {
        Set<String> books = category.getBooks().stream().map(book -> book.getTitle()).collect(Collectors.toSet());
        return new CategoryDTO(
            category.getId(),
            category.getName(),
            books
        );
    }

    public static Category toCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
}