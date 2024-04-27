package org.example.service;
import org.example.repository.BookRepository;

import org.example.model.Book;
import org.example.repository.Database;

import java.util.List;

public class BookService {

    private  Database database;
    private BookRepository bookRepository = new BookRepository();

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /*public Book getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.delete(id);
    }*/
}