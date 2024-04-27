package org.example.model;

import java.sql.Date;

public class Book {
    private int id;
    private String title;
    private int author_id ;
    private int genre_id;
    private String language;
    private Date publication_date;
    private int num_pages;

    public void setId(int id) {
        this.id=id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor_id(int authorId) {
        this.author_id = authorId;
    }

    public void setGenre_id(int genreId) {
        this.genre_id=genreId;
    }

    public void setLanguage(String language) {
        this.language=language;
    }

    public void setPublication_date(Date publicationDate) {
        this.publication_date = publicationDate;
    }

    public void setNum_pages(int numPages) {
        this.num_pages = numPages;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public String getLanguage() {
        return language;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public int getNum_pages() {
        return num_pages;
    }
}