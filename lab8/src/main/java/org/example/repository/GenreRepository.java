package org.example.repository;

import org.example.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository {

    private Connection connection;

    public GenreRepository(Connection connection) {
        this.connection = connection;
    }

    public GenreRepository() {

        Database dbInstance = Database.getInstance();
        Connection conn = dbInstance.getConnection();

        if (conn != null) {
            System.out.println("Conexiunea este activă și gata de utilizare.");
            this.connection = conn;
        } else {
            System.out.println("Conexiunea nu a fost stabilită.");
        }

    }

    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM genres");
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setGenre_id(rs.getInt("genre_id"));
                genre.setName(rs.getString("title"));
                genre.setDescription(rs.getString("description"));
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    public Genre findById(int genre_id) {
        Genre newGenre = new Genre();
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM genres WHERE genre_id=?");

            statement.setInt(1, genre_id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                Genre genre = new Genre();
                genre.setGenre_id(rs.getInt("genre_id"));
                genre.setName(rs.getString("name"));
                genre.setDescription(rs.getString("description"));
                newGenre = genre;

            }
            rs.close();
            statement.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return newGenre;
    }
    public Genre findbyName(String name){
        Genre newGenre=new Genre();
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM genres WHERE name=?");

            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Genre genre = new Genre();
                genre.setGenre_id(rs.getInt("genre_id"));
                genre.setName(rs.getString("name"));
                genre.setDescription(rs.getString("description"));
                newGenre=genre;
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return newGenre;
    }
    public void save(Genre genre) {

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO genres(genre_id, name, description) values(?,?,?)");

            statement.setInt(1, genre.getGenre_id());
            statement.setString(2, genre.getName());
            statement.setString(3, genre.getDescription());
            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }
}
