package org.example.bookapi.dao;

import org.example.bookapi.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorDao extends JpaRepository<Authors, Integer> {

    @Query("SELECT a FROM Authors a WHERE a.name = :name")
    Optional<Authors> findByName(String name);

    @Query("SELECT a from Authors a WHERE a.authorCode = :authorCode")
    Optional<Authors> findByAuthorCode(String authorCode);
}
