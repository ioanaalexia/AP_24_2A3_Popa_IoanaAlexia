package org.example.model;

import lombok.AllArgsConstructor;

import java.sql.Date;


public class Book extends BaseEntity{
    private String title;
    private String language;
    private Date publication_date;
    private int num_pages;

    private String autorName;

    private String genre;

    public Book(){

    }

    public Book(String title, String language, Date publication_date, int num_pages,String autorName,String genre) {
        this.title = title;
        this.language = language;
        this.publication_date = publication_date;
        this.num_pages = num_pages;
        this.autorName=autorName;
        this.genre=genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        super.setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public int getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(int num_pages) {
        this.num_pages = num_pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", publication_date=" + publication_date +
                ", num_pages=" + num_pages +
                '}';
    }
}