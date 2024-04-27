package org.example.repository;

import org.example.controller.AuthorController;
import org.example.controller.GenreController;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Genre;
import org.example.service.AuthorService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookRepository {

    private Connection connection;

    public BookRepository(Connection connection) {
        this.connection = connection;
    }

    public BookRepository() {

        Database dbInstance = Database.getInstance();
        Connection conn = dbInstance.getConnection();

        if (conn != null) {
            System.out.println("Conexiunea este activă și gata de utilizare.");
            this.connection = conn;
        } else {
            System.out.println("Conexiunea nu a fost stabilită.");
        }

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
        Book newBook = new Book();
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE id=?");

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setLanguage(rs.getString("language"));
                book.setPublication_date(rs.getDate("publication_date"));
                book.setNum_pages(rs.getInt("num_pages"));
                newBook = book;

            }
            rs.close();
            statement.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return newBook;
    }

    public void save(Book book) {

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO books(title,language,publication_date,num_pages,author_id,genre_id)  values(?,?,?,?,?,?)");

            AuthorController authorController = new AuthorController();
            Author author = authorController.getAuthorByName(book.getAutorName());

            GenreController genreController=new GenreController();
            Genre genre=genreController.getGenreByName(book.getGenre());
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getLanguage());
            statement.setDate(3, book.getPublication_date());
            statement.setInt(4, book.getNum_pages());
            statement.setInt(5, author.getId());
            statement.setInt(6,genre.getGenre_id());
            statement.executeUpdate();


        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public Book findByName(String name) {
        Book newBook = new Book();
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE title=?");

            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("name"));
              newBook=book;
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return newBook;
    }

}
