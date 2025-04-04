package com.example.biblo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblo.entity.Author;

public interface AuthorDAO extends JpaRepository<Author, Integer> {
    Optional<Author> findByName(String name);
}
