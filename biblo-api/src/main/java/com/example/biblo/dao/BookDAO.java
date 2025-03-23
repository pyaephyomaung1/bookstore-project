package com.example.biblo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblo.entity.Book;

public interface BookDAO extends JpaRepository<Book, Integer> {
    
}
