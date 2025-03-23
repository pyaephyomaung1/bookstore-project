package com.example.biblo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblo.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
    
}
