package org.example.service;
import org.example.repository.BookRepository;

import org.example.model.Book;
import org.example.repository.Database;

import java.util.List;

public class BookService {

    private BookRepository bookRepository = new BookRepository();

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBookByName(String name){
        return  bookRepository.findByName(name);
    }

}