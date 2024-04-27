package org.example.repository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataImporter {
    public static void importData(String filePath) {
        try (Connection connection = DatabaseConfig.getDataSource().getConnection();
             Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim()
                     .withIgnoreEmptyLines()
                     .withHeader("bookID", "title", "authors", "average_rating", "isbn", "isbn13", "language_code", "num_pages", "ratings_count", "text_reviews_count", "publication_date", "publisher"))) {

            String sql = "INSERT INTO books_imp (bookID, title, authors, average_rating, isbn, isbn13, language_code, num_pages, ratings_count, text_reviews_count, publication_date, publisher) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (CSVRecord record : csvParser) {
                statement.setInt(1, Integer.parseInt(record.get("bookID")));
                statement.setString(2, record.get("title"));
                statement.setString(3, record.get("authors"));
                statement.setBigDecimal(4, new BigDecimal(record.get("average_rating")));
                statement.setString(5, record.get("isbn"));
                statement.setString(6, record.get("isbn13"));
                statement.setString(7, record.get("language_code"));
                statement.setInt(8, Integer.parseInt(record.get("num_pages").trim())); // Adjusted for space in CSV header
                statement.setInt(9, Integer.parseInt(record.get("ratings_count")));
                statement.setInt(10, Integer.parseInt(record.get("text_reviews_count")));
                statement.setDate(11, java.sql.Date.valueOf(LocalDate.parse(record.get("publication_date"), DateTimeFormatter.ofPattern("M/d/yyyy"))));
                statement.setString(12, record.get("publisher"));

                statement.addBatch();


            }

            statement.executeBatch();
            System.out.println("Data import completed successfully.");

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
