package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;
import java.util.List;

public class BookController {

    private BookService bookService = new BookService();

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book getBookById(int id) {
        return bookService.getBookById(id);
    }

    public void saveBook(Book book) {
        bookService.saveBook(book);
    }

    public void deleteBook(int id) {
        bookService.deleteBook(id);
    }
}