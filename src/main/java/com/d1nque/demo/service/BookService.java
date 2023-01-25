package com.d1nque.demo.service;

import com.d1nque.demo.model.Book;
import com.d1nque.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    }

    public List<Book> getBooksByAuthor(String author){
        return bookRepository.findBooksByAuthor(author);
    }

    public List<Book> getBooksByCategory(String category){
        return bookRepository.findBooksByCategory_CategoryName(category);
    }

    @Transactional
    public Book saveNewBook(Book book){
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBookById(Long id){
        bookRepository.deleteBooks(id);
    }

    @Transactional
    public void updateBook(Book book){
        bookRepository.updateBooks(book);
    }

    public List<Book> getBooksByAuthorAndCategory(String author, String category){
        return bookRepository.findBooksByAuthorAndCategory_CategoryName(author, category);
    }

    public void deleteAll(){
        bookRepository.deleteAll();
    }
}
