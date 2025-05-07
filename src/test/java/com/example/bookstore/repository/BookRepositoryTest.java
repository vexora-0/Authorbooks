package com.example.bookstore.repository;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldFindAllBooksWithAuthors() {
        // given
        Author author1 = new Author("Test Author 1", "Bio 1", "test1@example.com");
        Author author2 = new Author("Test Author 2", "Bio 2", "test2@example.com");
        
        entityManager.persist(author1);
        entityManager.persist(author2);
        
        Book book1 = new Book("Test Book 1", "Description 1", "ISBN-1", LocalDate.now(), new BigDecimal("19.99"));
        book1.setAuthor(author1);
        
        Book book2 = new Book("Test Book 2", "Description 2", "ISBN-2", LocalDate.now(), new BigDecimal("29.99"));
        book2.setAuthor(author2);
        
        Book book3 = new Book("Test Book 3", "Description 3", "ISBN-3", LocalDate.now(), new BigDecimal("39.99"));
        book3.setAuthor(author1);
        
        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.flush();

        // when
        List<Book> books = bookRepository.findAllBooksWithAuthors();

        // then
        assertThat(books).hasSize(3);
        assertThat(books).extracting(Book::getTitle).containsExactlyInAnyOrder(
                "Test Book 1", "Test Book 2", "Test Book 3");
        
        // Verify that authors are loaded
        assertThat(books).allSatisfy(book -> {
            assertThat(book.getAuthor()).isNotNull();
            assertThat(book.getAuthor().getName()).isNotEmpty();
        });
    }
}