package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "BookEntity.findByTitle", query = "SELECT b FROM BookEntity b WHERE b.title LIKE :title"),
        @NamedQuery(name = "BookEntity.findById", query = "SELECT b FROM BookEntity b WHERE b.id = :id")
})
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "publication_date")
    private Date publicationDate;

    @Column(name = "num_pages")
    private Integer numPages;

    public BookEntity(Integer id, String title, String language, Date publicationDate, Integer numPages) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.publicationDate = publicationDate;
        this.numPages = numPages;
    }

    public BookEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }
}
