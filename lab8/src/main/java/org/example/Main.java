package org.example;

import org.example.controller.AuthorController;
import org.example.controller.BookController;
import org.example.controller.GenreController;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Genre;
import org.example.repository.Database;
import org.example.repository.GenreRepository;


import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[])  {
        Database dbInstance = Database.getInstance();
        Connection conn = dbInstance.getConnection();
        if (conn != null) {
            System.out.println("Conexiunea este activă și gata de utilizare.");
        } else {
            System.out.println("Conexiunea nu a fost stabilită.");
        }

        List<Book> books=new ArrayList<>();
        BookController library=new BookController();
        books=library.getAllBooks();
        books.forEach(System.out::println);
        System.out.println("Cartea cu id-ul 1 este:"+library.getBookById(1));

        AuthorController autori=new AuthorController();
        autori.saveAuthor(new Author("Lev Tolstoi"));

        GenreController genreController=new GenreController();
        genreController.saveGenre(new Genre("tragedie"));

        Book book1 = new Book( "Anna Karenina",  "romana", Date.valueOf("2017-04-27"), 200,"Lev Tolstoi","tragedie");
        library.saveBook(book1);
        Book book2=library.getBookByName("Anna Karenkina");
        System.out.println(book2);


        List<Author> authors = new ArrayList<>();
        AuthorController librarie = new AuthorController();
        authors=librarie.getAllAuthor();
        authors.forEach(System.out::println);
    }
        // Path to the CSV file to be imported
       /* String csvFilePath = "C:\\Users\\User\\Desktop\\github\\AP_24_2A3_MAXIM_MARIA\\lab8\\books.csv";

        // Call the importData method from the DataImporter class
        DataImporter.importData(csvFilePath);

        //System.out.println("Data import completed successfully!");
    }*/

}