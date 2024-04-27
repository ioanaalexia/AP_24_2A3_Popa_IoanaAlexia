package org.example;

import org.example.repository.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String args[])  {
        /*DatabaseInitializer database=new DatabaseInitializer();
        database.initialize(); // Initialize the database structure
*/
        Database dbInstance = Database.getInstance();
        Connection conn = dbInstance.getConnection();
        if (conn != null) {
            System.out.println("Conexiunea este activă și gata de utilizare.");
        } else {
            System.out.println("Conexiunea nu a fost stabilită.");
        }

        /*Database.ensureConnectionIsOpen(); // Ensure the database connection is open

        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.create("George Orwell"); // Add an author

        String authorName = authorDAO.findById(1); // Retrieve author by ID
        if (authorName != null) {
            System.out.println("Author found: " + authorName);
        } else {
            System.out.println("No author found.");
        }
        //controler,service,repo-MVC

        Database.closeConnection(); // Close the connection
    }*/
    }
}