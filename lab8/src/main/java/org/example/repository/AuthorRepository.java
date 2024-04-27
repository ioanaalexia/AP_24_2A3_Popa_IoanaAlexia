package org.example.repository;

import org.example.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {
    private static Connection connection;
    public AuthorRepository(Connection connection) {
        this.connection = connection;
    }

    public AuthorRepository(){

        Database dbInstance = Database.getInstance();
        Connection conn = dbInstance.getConnection();

        if (conn != null) {
            System.out.println("Conexiunea este activă și gata de utilizare.");
            this.connection=conn;
        } else {
            System.out.println("Conexiunea nu a fost stabilită.");
        }
    }

    public static List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM authors");
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author findById(int id) {
        Author newAuthor=new Author();
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors WHERE id=?");

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                newAuthor=author;
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return newAuthor;
    }

    public Author findbyName(String name){
        Author newAuthor=new Author();
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors WHERE name=?");

            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                newAuthor=author;
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return newAuthor;
    }

    public void save(Author author) {

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO authors(name)  values(?)");

            statement.setString(1, author.getName());
            statement.executeUpdate();


        }catch (SQLException e) {

            e.printStackTrace();
        }

    }
}