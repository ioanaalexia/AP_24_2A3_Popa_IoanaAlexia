package org.example.repository;

import org.example.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookRepository {
    private Connection connection;

    public BookRepository(Connection connection) {
        this.connection = connection;
    }
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor_id((rs.getInt("author_id"));
                book.setGenre_id((rs.getInt("genre_id"));
                book.setLanguage(rs.getString("language"));
                book.setPublication_date(rs.getDate("publication_date"));
                book.setNum_pages(rs.getInt("num_pages"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book findById(int id) {
        // implementația metodei pentru a găsi o carte după ID
    }

    public Book save(Book book) {
        // implementația metodei pentru a salva sau actualiza o carte
    }

    public void delete(int id) {
        // implementația metodei pentru a șterge o carte
    }
}
