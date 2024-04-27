package org.example.controller;

import org.example.model.Author;
import org.example.service.AuthorService;
import java.util.List;

public class AuthorController {

    private AuthorService authorService = new AuthorService();

    public List<Author> getAllAuthor() {
        return authorService.getAllAuthors();
    }


    public Author getAuthorById(int id) {
        return authorService.getAuthorById(id);
    }

    public Author getAuthorByName(String name){
        return authorService.getAuthorByName(name);
    }

    public void saveAuthor(Author author) {
        authorService.saveAuthor(author);
    }


}