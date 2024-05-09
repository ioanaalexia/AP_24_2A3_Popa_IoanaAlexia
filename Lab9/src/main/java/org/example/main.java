package org.example;
import org.example.entities.BookEntity;
import org.example.repositories.BookRepository;
import java.awt.print.Book;
import java.util.Date;
import java.util.List;

public class main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository(JPAUtil.getEntityManagerFactory());


        // Adding a new book
        BookEntity newBook = new BookEntity(123, "Pride and Prejudice", "English", new Date(), 432);
        bookRepository.createOrUpdate(newBook);

        // Finding a book by ID
        BookEntity foundBook = bookRepository.findById(1);
        if (foundBook != null) {
            System.out.println("Found Book: " + foundBook.getTitle());
        } else {
            System.out.println("No book found with the given ID.");
        }

        // Finding books by title
        List<BookEntity> books = bookRepository.findByTitle("Pride");
        if (!books.isEmpty()) {
            for (BookEntity book : books) {
                System.out.println("Book Found: " + book.getTitle());
            }
        } else {
            System.out.println("No books found with the given title.");
        }
        JPAUtil.close();
    }
}
