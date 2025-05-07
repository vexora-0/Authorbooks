package com.example.bookstore.config;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    @Autowired
    public DataLoader(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) {
        // Create 10 authors with books
        createSampleData();
    }

    private void createSampleData() {
        // Author 1 with books
        Author author1 = new Author("J.K. Rowling", "British author best known for the Harry Potter series", "jkrowling@example.com");
        
        Book book1 = new Book("Harry Potter and the Philosopher's Stone", 
                "The first book in the Harry Potter series", "978-0747532743", 
                LocalDate.of(1997, 6, 26), new BigDecimal("19.99"));
        
        Book book2 = new Book("Harry Potter and the Chamber of Secrets", 
                "The second book in the Harry Potter series", "978-0747538486", 
                LocalDate.of(1998, 7, 2), new BigDecimal("21.99"));
        
        author1.addBook(book1);
        author1.addBook(book2);

        // Author 2 with books
        Author author2 = new Author("George Orwell", "English novelist and essayist", "georgeorwell@example.com");
        
        Book book3 = new Book("1984", 
                "A dystopian novel set in a totalitarian world", "978-0451524935", 
                LocalDate.of(1949, 6, 8), new BigDecimal("15.99"));
        
        Book book4 = new Book("Animal Farm", 
                "An allegorical novella about the Russian Revolution", "978-0451526342", 
                LocalDate.of(1945, 8, 17), new BigDecimal("12.99"));
        
        author2.addBook(book3);
        author2.addBook(book4);

        // Author 3 with books
        Author author3 = new Author("Jane Austen", "English novelist known for romantic fiction", "janeausten@example.com");
        
        Book book5 = new Book("Pride and Prejudice", 
                "A romantic novel of manners", "978-0141439518", 
                LocalDate.of(1813, 1, 28), new BigDecimal("9.99"));
        
        Book book6 = new Book("Emma", 
                "A novel about youthful hubris and romantic misunderstandings", "978-0141439587", 
                LocalDate.of(1815, 12, 25), new BigDecimal("11.99"));
        
        author3.addBook(book5);
        author3.addBook(book6);

        // Author 4
        Author author4 = new Author("Mark Twain", "American writer and humorist", "marktwain@example.com");
        
        Book book7 = new Book("The Adventures of Tom Sawyer", 
                "A novel about a boy growing up along the Mississippi River", "978-0143039563", 
                LocalDate.of(1876, 12, 1), new BigDecimal("10.99"));
        
        author4.addBook(book7);

        // Author 5
        Author author5 = new Author("Agatha Christie", "English writer known for detective novels", "agathachristie@example.com");
        
        Book book8 = new Book("Murder on the Orient Express", 
                "A detective novel featuring Hercule Poirot", "978-0062693662", 
                LocalDate.of(1934, 1, 1), new BigDecimal("14.99"));
        
        Book book9 = new Book("And Then There Were None", 
                "A mystery novel", "978-0062073488", 
                LocalDate.of(1939, 11, 6), new BigDecimal("13.99"));
        
        author5.addBook(book8);
        author5.addBook(book9);

        // Author 6
        Author author6 = new Author("J.R.R. Tolkien", "English writer and philologist", "jrrtolkien@example.com");
        
        Book book10 = new Book("The Hobbit", 
                "A children's fantasy novel", "978-0547928227", 
                LocalDate.of(1937, 9, 21), new BigDecimal("18.99"));
        
        Book book11 = new Book("The Lord of the Rings", 
                "An epic high-fantasy novel", "978-0618640157", 
                LocalDate.of(1954, 7, 29), new BigDecimal("29.99"));
        
        author6.addBook(book10);
        author6.addBook(book11);

        // Author 7
        Author author7 = new Author("Ernest Hemingway", "American novelist and short-story writer", "ernesthemingway@example.com");
        
        Book book12 = new Book("The Old Man and the Sea", 
                "A short novel about an aging Cuban fisherman", "978-0684801223", 
                LocalDate.of(1952, 9, 1), new BigDecimal("12.99"));
        
        author7.addBook(book12);

        // Author 8
        Author author8 = new Author("F. Scott Fitzgerald", "American novelist", "fscottfitzgerald@example.com");
        
        Book book13 = new Book("The Great Gatsby", 
                "A novel set in the Jazz Age", "978-0743273565", 
                LocalDate.of(1925, 4, 10), new BigDecimal("14.99"));
        
        author8.addBook(book13);

        // Author 9
        Author author9 = new Author("Charles Dickens", "English writer and social critic", "charlesdickens@example.com");
        
        Book book14 = new Book("A Tale of Two Cities", 
                "A historical novel set in London and Paris", "978-0141439600", 
                LocalDate.of(1859, 4, 30), new BigDecimal("11.99"));
        
        Book book15 = new Book("Great Expectations", 
                "A coming-of-age novel", "978-0141439563", 
                LocalDate.of(1861, 8, 1), new BigDecimal("12.99"));
        
        author9.addBook(book14);
        author9.addBook(book15);

        // Author 10
        Author author10 = new Author("William Shakespeare", "English playwright and poet", "williamshakespeare@example.com");
        
        Book book16 = new Book("Hamlet", 
                "A tragedy", "978-0743477123", 
                LocalDate.of(1603, 1, 1), new BigDecimal("8.99"));
        
        Book book17 = new Book("Romeo and Juliet", 
                "A tragedy about two young lovers", "978-0743477116", 
                LocalDate.of(1597, 1, 1), new BigDecimal("7.99"));
        
        author10.addBook(book16);
        author10.addBook(book17);

        // Save all authors (which will cascade to books)
        List<Author> authors = Arrays.asList(author1, author2, author3, author4, author5, 
                                             author6, author7, author8, author9, author10);
        authorRepository.saveAll(authors);
    }
} 