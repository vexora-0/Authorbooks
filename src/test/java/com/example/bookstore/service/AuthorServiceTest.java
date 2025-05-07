package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        authorService = new AuthorServiceImpl(authorRepository);
    }

    @Test
    public void shouldFindAllAuthors() {
        // given
        Author author1 = new Author("Author 1", "Bio 1", "author1@example.com");
        author1.setId(1L);
        Author author2 = new Author("Author 2", "Bio 2", "author2@example.com");
        author2.setId(2L);
        
        List<Author> authors = Arrays.asList(author1, author2);
        
        when(authorRepository.findAll()).thenReturn(authors);

        // when
        List<Author> result = authorService.findAllAuthors();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(Author::getName).containsExactly("Author 1", "Author 2");
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindAuthorById() {
        // given
        Author author = new Author("Author 1", "Bio 1", "author1@example.com");
        author.setId(1L);
        
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        // when
        Optional<Author> result = authorService.findAuthorById(1L);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Author 1");
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldSaveAuthor() {
        // given
        Author author = new Author("New Author", "New Bio", "new@example.com");
        
        when(authorRepository.save(any(Author.class))).thenAnswer(invocation -> {
            Author savedAuthor = invocation.getArgument(0);
            savedAuthor.setId(1L);
            return savedAuthor;
        });

        // when
        Author savedAuthor = authorService.saveAuthor(author);

        // then
        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getId()).isEqualTo(1L);
        assertThat(savedAuthor.getName()).isEqualTo("New Author");
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void shouldDeleteAuthor() {
        // given
        Long authorId = 1L;
        
        // when
        authorService.deleteAuthor(authorId);

        // then
        verify(authorRepository, times(1)).deleteById(authorId);
    }
}