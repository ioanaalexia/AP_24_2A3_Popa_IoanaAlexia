package org.example.service;
import org.example.model.Author;
import org.example.repository.GenreRepository;

import org.example.model.Genre;
import org.example.repository.Database;

import java.util.List;

public class GenreService {

    private GenreRepository genreRepository = new GenreRepository();

    public List<Genre> getAllGenres() {

        return genreRepository.findAll();
    }

    public Genre getGenreById(int id) {

        return genreRepository.findById(id);
    }
    public Genre getGenreByName(String name){

        return genreRepository.findbyName(name);
    }

    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }


}