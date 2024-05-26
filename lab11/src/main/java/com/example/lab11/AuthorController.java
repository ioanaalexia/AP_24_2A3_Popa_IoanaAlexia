package com.example.demo.controller;

import com.example.demo.model.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @GetMapping
    public List<Author> getAllAuthors() {
        return Arrays.asList(
                new Author(1L, "Author One"),
                new Author(2L, "Author Two")
        );
    }
}
