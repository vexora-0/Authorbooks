package com.example.bookstore.controller;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.AuthorService;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.findAllBooksWithAuthors();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "book/form";
    }

    @PostMapping
    public String createBook(@ModelAttribute Book book, @RequestParam Long authorId, 
                           RedirectAttributes redirectAttributes) {
        try {
            Optional<Author> author = authorService.findAuthorById(authorId);
            if (author.isPresent()) {
                book.setAuthor(author.get());
                bookService.saveBook(book);
                redirectAttributes.addFlashAttribute("successMessage", "Book created successfully");
                return "redirect:/books";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Author not found");
                return "redirect:/books/new";
            }
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating book: " + e.getMessage());
            return "redirect:/books/new";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Book> book = bookService.findBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("authors", authorService.findAllAuthors());
            return "book/form";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Book not found");
            return "redirect:/books";
        }
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, 
                           @RequestParam Long authorId, RedirectAttributes redirectAttributes) {
        try {
            Optional<Author> author = authorService.findAuthorById(authorId);
            if (author.isPresent()) {
                book.setId(id);
                book.setAuthor(author.get());
                bookService.saveBook(book);
                redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully");
                return "redirect:/books";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Author not found");
                return "redirect:/books/" + id + "/edit";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating book: " + e.getMessage());
            return "redirect:/books/" + id + "/edit";
        }
    }
} 