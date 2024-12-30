package org.example.bookapi.util;

import org.example.bookapi.dto.AuthorDTO;
import org.example.bookapi.dto.BookDTO;
import org.example.bookapi.entity.Authors;
import org.example.bookapi.entity.Books;


import java.util.stream.Collectors;


public class EntityUtils {


    public static BookDTO toBookDTO(Books book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                book.getPublicationDate(),
                book.getDescription(),
                book.getBookCoverUrl(),
                book.getAuthor().getName(),
                book.getCategory().getCategoryName()
        );
    }



    public static AuthorDTO toAuthorDTO(Authors author) {
        return new AuthorDTO(
                author.getName(),
                author.getNationality(),
                author.getBirthDate(),
                author.getBiography(),
                author.getAuthorProfileUrl(),
                author.getAuthorCode(),
                author.getBooks().stream()
                        .map(book -> new BookDTO(
                                book.getId(),
                                book.getTitle(),
                                book.getPrice(),
                                book.getPublicationDate(),
                                book.getDescription(),
                                book.getBookCoverUrl(), 
                                book.getAuthor().getName(),
                                book.getCategory().getCategoryName()
                        )).collect(Collectors.toList())
        );
    }


}
