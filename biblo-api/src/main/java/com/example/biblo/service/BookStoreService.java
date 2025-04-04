package com.example.biblo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.biblo.dao.AuthorDAO;
import com.example.biblo.dao.BookDAO;
import com.example.biblo.dao.CategoryDAO;
import com.example.biblo.dto.AuthorDTO;
import com.example.biblo.dto.BookDTO;
import com.example.biblo.dto.CategoryDTO;
import com.example.biblo.entity.Author;
import com.example.biblo.entity.Book;
import com.example.biblo.entity.Category;
import com.example.biblo.utils.EntityUtil;

import jakarta.transaction.Transactional;
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
    @Transactional
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = EntityUtil.toBook(bookDTO);
        book = bookDAO.save(book);
        return EntityUtil.toBookDTO(book);
    }

    @Override
    @Transactional
    public BookDTO updateBook(int id, BookDTO bookDTO) {
        Book book = bookDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        // Update basic fields
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setPublishDate(LocalDate.parse(bookDTO.getPublishDate()));
        book.setDescription(bookDTO.getDescription());
        book.setBookCover(bookDTO.getBookCover());

        // Handle Author update
        if (bookDTO.getAuthorName() != null &&
                (book.getAuthor() == null || !book.getAuthor().getName().equals(bookDTO.getAuthorName()))) {
            Author author = authorDAO.findByName(bookDTO.getAuthorName())
                    .orElseThrow(() -> new RuntimeException("Author not found: " + bookDTO.getAuthorName()));
            book.setAuthor(author);
        }

        // Handle Categories update
        if (bookDTO.getCategories() != null) {
            book.clearCategories();
            for (String categoryName : bookDTO.getCategories()) {
                Category category = categoryDAO.findByName(categoryName)
                        .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));
                book.addCategory(category);
            }
        }

        Book updatedBook = bookDAO.save(book);
        return EntityUtil.toBookDTO(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        Book book = bookDAO.findById(id).orElseThrow(() -> new RuntimeException("Book not found with this id: " + id));
        book.clearCategories();
        bookDAO.delete(book);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = EntityUtil.toAuthor(authorDTO);
        author = authorDAO.save(author);
        return EntityUtil.toAuthorDTO(author);
    }

    @Override
    public AuthorDTO updateAuthor(int id, AuthorDTO authorDTO) {
        Author author = authorDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());
        author.setNationality(authorDTO.getNationality());
        author.setBirthDate(LocalDate.parse(authorDTO.getBirthDate()));
        author.setAuthorProfile(authorDTO.getAuthorProfile());
        Author updatedAuthor = authorDAO.save(author);
        return EntityUtil.toAuthorDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthor(int id) {
        Author author = authorDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        for (Book book : new ArrayList<>(author.getBooks())) {
            book.setAuthor(null);
        }
        authorDAO.delete(author);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = EntityUtil.toCategory(categoryDTO);
        category = categoryDAO.save(category);
        return EntityUtil.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
        Category category = categoryDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setName(categoryDTO.getName());
        Category updatedCategory = categoryDAO.save(category);
        return EntityUtil.toCategoryDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(int id) {
        Category category = categoryDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        for (Book book : new ArrayList<>(category.getBooks())) {
            book.removeCategory(category);
        }
        categoryDAO.delete(category);
    }

}
