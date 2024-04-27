package org.example.service;

import org.example.model.Author;
import org.example.model.Book;
import org.example.repository.AuthorRepository;

import java.util.List;

public class AuthorService {
    private AuthorRepository authorRepository = new AuthorRepository();

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }


    public Author getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Author getAuthorByName(String name){
        return authorRepository.findbyName(name);
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

}