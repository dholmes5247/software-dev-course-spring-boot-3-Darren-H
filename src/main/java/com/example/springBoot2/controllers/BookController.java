package com.example.springBoot2.controllers;

import com.example.springBoot2.models.Book; //  Correct Book class
import com.example.springBoot2.repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll(); //  Returns List<com.example.springBoot2.models.Book>
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        Optional<Book> result = bookRepository.findById(id);
        return result.orElse(null);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        updatedBook.setId(id);
        return bookRepository.save(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
    }
}
