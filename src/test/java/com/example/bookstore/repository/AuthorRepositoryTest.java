package com.example.bookstore.repository;

import com.example.bookstore.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldFindAllAuthors() {
        // given
        Author author1 = new Author("Test Author 1", "Bio 1", "test1@example.com");
        Author author2 = new Author("Test Author 2", "Bio 2", "test2@example.com");
        
        entityManager.persist(author1);
        entityManager.persist(author2);
        entityManager.flush();

        // when
        List<Author> authors = authorRepository.findAll();

        // then
        assertThat(authors).hasSize(2);
        assertThat(authors).extracting(Author::getName).containsExactlyInAnyOrder("Test Author 1", "Test Author 2");
    }

    @Test
    public void shouldFindAuthorById() {
        // given
        Author author = new Author("Test Author", "Bio", "test@example.com");
        entityManager.persist(author);
        entityManager.flush();

        // when
        Optional<Author> found = authorRepository.findById(author.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Author");
        assertThat(found.get().getBio()).isEqualTo("Bio");
        assertThat(found.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void shouldSaveAuthor() {
        // given
        Author author = new Author("New Author", "New Bio", "new@example.com");

        // when
        Author saved = authorRepository.save(author);

        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("New Author");
    }
} 