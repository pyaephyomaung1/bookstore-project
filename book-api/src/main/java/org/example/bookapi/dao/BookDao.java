package org.example.bookapi.dao;

import org.example.bookapi.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BookDao extends JpaRepository<Books, Integer> {

    @Query("""
       SELECT b FROM Books b JOIN FETCH b.author JOIN FETCH b.category
""")
    List<Books> getAllBookInfo();

    @Query("SELECT b FROM Books b WHERE b.title = :title")
    Optional<Books> findByTitle(@Param("title") String title);
}
