package com.example.biblo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.biblo.dao.AuthorDAO;
import com.example.biblo.dao.BookDAO;
import com.example.biblo.dao.CategoryDAO;
import com.example.biblo.dto.AuthorDTO;
import com.example.biblo.dto.BookDTO;
import com.example.biblo.dto.CategoryDTO;
import com.example.biblo.entity.Book;
import com.example.biblo.utils.EntityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookStoreService implements BookStoreInterface {
    private final AuthorDAO authorDAO;
    private final BookDAO bookDAO;
    private final CategoryDAO categoryDAO;

    @Override
    public List<BookDTO> getBooks() {
        return bookDAO.findAll().stream().map(EntityUtil::toBookDTO).collect(Collectors.toList());
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        return authorDAO.findAll().stream().map(EntityUtil::toAuthorDTO).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryDAO.findAll().stream().map(EntityUtil::toCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = EntityUtil.toBook(bookDTO);
        book = bookDAO.save(book);
        return EntityUtil.toBookDTO(book);
    }

    @Override
    public BookDTO updateBook(int id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookDAO.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setAuthorId(bookDTO.getAuthorId());
            book.setCategoryId(bookDTO.getCategoryId());
            book = bookDAO.save(book);
            return EntityUtil.toBookDTO(book);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    @Override
    public void deleteBook(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBook'");
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAuthor'");
    }

    @Override
    public AuthorDTO updateAuthor(int id, AuthorDTO authorDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAuthor'");
    }

    @Override
    public void deleteAuthor(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAuthor'");
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCategory'");
    }

    @Override
    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCategory'");
    }

    @Override
    public void deleteCategory(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }

}
