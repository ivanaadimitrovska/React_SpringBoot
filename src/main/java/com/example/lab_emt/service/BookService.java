package com.example.lab_emt.service;

import com.example.lab_emt.model.Author;
import com.example.lab_emt.model.Book;
import com.example.lab_emt.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);
    List<Book> listAll();
    Optional<Book> create(String name, Category category, String authorName, String authorSurname, String countryName, Integer availableCopies);
    Optional<Book> create_1(String name, Category category, Author author, Integer availableCopies);
    Optional<Book> update(Long id, String name, Category category, String authorName, String authorSurname, String countryName, Integer availableCopies);
    void delete(Long id);
    Book rentedBook(Long id);
}
