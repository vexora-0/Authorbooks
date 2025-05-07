package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooks();
    List<Book> findAllBooksWithAuthors();
    Optional<Book> findBookById(Long id);
    Book saveBook(Book book);
    void deleteBook(Long id);
} 