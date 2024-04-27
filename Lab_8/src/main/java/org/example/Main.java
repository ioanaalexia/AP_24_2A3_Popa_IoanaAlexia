package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String args[])  {
        DatabaseInitializer database=new DatabaseInitializer();
        database.initialize(); // Initialize the database structure

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