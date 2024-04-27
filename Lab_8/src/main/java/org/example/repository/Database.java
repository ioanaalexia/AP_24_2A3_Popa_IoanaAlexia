package org.example.repository;

import java.sql.*;

public class Database {//ca singleton

    private static Database instance;
    private Connection connection;

    private Database() {
        String url = "jdbc:mysql://localhost:3306/library";
        String utilizator = "root";
        String parola = "Valeria_2004";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, utilizator, parola);
            System.out.println("Conexiunea la baza de date a fost realizată cu succes!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driverul MySQL JDBC nu a putut fi găsit!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Conexiunea la baza de date a eșuat!");
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
