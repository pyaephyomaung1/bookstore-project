package com.example.biblo.service;

import java.util.List;

import com.example.biblo.dto.AuthorDTO;
import com.example.biblo.dto.CategoryDTO;
import com.example.biblo.dto.BookDTO;

public interface BookStoreInterface {
    public List<AuthorDTO> getAuthors();
    public List<BookDTO> getBooks();
    public List<CategoryDTO> getCategories();

    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook( int id,BookDTO bookDTO);
    void deleteBook(int id);

    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO updateAuthor(int id, AuthorDTO authorDTO);
    void deleteAuthor(int id);

    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(int id, CategoryDTO categoryDTO);
    void deleteCategory(int id);
}
