package com.d1nque.demo.controller;

import com.d1nque.demo.model.Book;
import com.d1nque.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Book getBookById(@PathVariable int id){
        return bookService.getBookById((long) id);
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author){
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category){
        return bookService.getBooksByCategory(category);
    }

    @PostMapping(value = "/create-book")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> createNewBook(@RequestBody Book book){
        bookService.saveNewBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PostMapping("/search-book")
    public List<Book> searchForBook(@RequestParam String author, @RequestParam String category){
        return bookService.getBooksByAuthorAndCategory(author, category);
    }

    @PostMapping("/delete/{id}")
    public void deleteBook(@PathVariable String id){
        bookService.deleteBookById(Long.parseLong(id));
    }

    @PostMapping("/update-book")
    public void updateBook(@RequestBody Book book){
        bookService.updateBook(book);
    }

}
