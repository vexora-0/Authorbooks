package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    public void setUp() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void shouldFindAllBooks() {
        // given
        Author author = new Author("Test Author", "Bio", "author@example.com");
        author.setId(1L);
        
        Book book1 = new Book("Book 1", "Description 1", "ISBN-1", LocalDate.now(), new BigDecimal("19.99"));
        book1.setId(1L);
        book1.setAuthor(author);
        
        Book book2 = new Book("Book 2", "Description 2", "ISBN-2", LocalDate.now(), new BigDecimal("29.99"));
        book2.setId(2L);
        book2.setAuthor(author);
        
        List<Book> books = Arrays.asList(book1, book2);
        
        when(bookRepository.findAll()).thenReturn(books);

        // when
        List<Book> result = bookService.findAllBooks();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(Book::getTitle).containsExactly("Book 1", "Book 2");
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindAllBooksWithAuthors() {
        // given
        Author author = new Author("Test Author", "Bio", "author@example.com");
        author.setId(1L);
        
        Book book1 = new Book("Book 1", "Description 1", "ISBN-1", LocalDate.now(), new BigDecimal("19.99"));
        book1.setId(1L);
        book1.setAuthor(author);
        
        Book book2 = new Book("Book 2", "Description 2", "ISBN-2", LocalDate.now(), new BigDecimal("29.99"));
        book2.setId(2L);
        book2.setAuthor(author);
        
        List<Book> books = Arrays.asList(book1, book2);
        
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(books);

        // when
        List<Book> result = bookService.findAllBooksWithAuthors();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(Book::getTitle).containsExactly("Book 1", "Book 2");
        assertThat(result).allSatisfy(book -> {
            assertThat(book.getAuthor()).isNotNull();
            assertThat(book.getAuthor().getName()).isEqualTo("Test Author");
        });
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }

    @Test
    public void shouldFindBookById() {
        // given
        Author author = new Author("Test Author", "Bio", "author@example.com");
        author.setId(1L);
        
        Book book = new Book("Book 1", "Description 1", "ISBN-1", LocalDate.now(), new BigDecimal("19.99"));
        book.setId(1L);
        book.setAuthor(author);
        
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // when
        Optional<Book> result = bookService.findBookById(1L);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Book 1");
        assertThat(result.get().getAuthor().getName()).isEqualTo("Test Author");
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldSaveBook() {
        // given
        Author author = new Author("Test Author", "Bio", "author@example.com");
        author.setId(1L);
        
        Book book = new Book("New Book", "New Description", "ISBN-NEW", LocalDate.now(), new BigDecimal("19.99"));
        book.setAuthor(author);
        
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> {
            Book savedBook = invocation.getArgument(0);
            savedBook.setId(1L);
            return savedBook;
        });

        // when
        Book savedBook = bookService.saveBook(book);

        // then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isEqualTo(1L);
        assertThat(savedBook.getTitle()).isEqualTo("New Book");
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void shouldDeleteBook() {
        // given
        Long bookId = 1L;
        
        // when
        bookService.deleteBook(bookId);

        // then
        verify(bookRepository, times(1)).deleteById(bookId);
    }
} 