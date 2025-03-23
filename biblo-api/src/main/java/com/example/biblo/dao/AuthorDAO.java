package com.example.biblo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblo.entity.Author;

public interface AuthorDAO extends JpaRepository<Author, Integer> {
    
}
