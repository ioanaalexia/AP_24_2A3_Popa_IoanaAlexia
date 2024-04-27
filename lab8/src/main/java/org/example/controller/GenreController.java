package org.example.controller;

import org.example.model.Genre;
import org.example.service.GenreService;

import java.util.List;

public class GenreController {

    private GenreService genreService = new GenreService();

    public List<Genre> getAllGenre() {
        return genreService.getAllGenres();
    }


    public Genre getGenreById(int genre_id) {
        return genreService.getGenreById(genre_id);
    }

    public Genre getGenreByName(String name){
        return genreService.getGenreByName(name);
    }

    public void saveGenre(Genre genre) {
        genreService.saveGenre(genre);
    }
}