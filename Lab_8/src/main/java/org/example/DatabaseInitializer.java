package org.example;

import java.sql.*;
public class DatabaseInitializer {
    public void initialize(){
            String url = "jdbc:mysql://localhost:3306/library";
            String utilizator = "root";
            String parola = "Valeria_2004";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection conexiune = DriverManager.getConnection(url, utilizator, parola);


                if (conexiune != null) {
                    System.out.println("Conexiunea la baza de date a fost realizată cu succes!");


                    // Crearea unui obiect Statement
                    Statement statement = conexiune.createStatement();

                    // Executarea unei interogări SELECT
                    String query = "SELECT * FROM authors"; // Înlocuiește "nume_tabel" cu numele real al tabelului
                    ResultSet resultSet = statement.executeQuery(query);

                    // Iterarea prin rezultat și afișarea informațiilor
                    while (resultSet.next()) {
                        // Exemplu: Extrageți o coloană numită "column_name"
                        String value = resultSet.getString("name");
                        System.out.println(value);
                    }

                    // Închiderea ResultSet și Statement
                    resultSet.close();
                    statement.close();


                } else {
                    System.out.println("Conexiunea la baza de date a eșuat!");
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Driverul MySQL JDBC nu a putut fi găsit!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Conexiunea la baza de date a eșuat!");
                e.printStackTrace();
            }
        }


}
