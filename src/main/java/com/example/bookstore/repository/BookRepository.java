package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    /**
     * Custom query that performs an inner join between Book and Author entities
     * @return List of books with their authors
     */
    @Query("SELECT b FROM Book b INNER JOIN b.author a")
    List<Book> findAllBooksWithAuthors();
} 