package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@Setter
@Getter

public class Book {
    private int id;
    private String title;
    private int author_id ;
    private int genre_id;
    private String language;
    private Date publication_date;
    private int num_pages;

}
