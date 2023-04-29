package com.example.lab_emt.service;

import com.example.lab_emt.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
    List<Author> listAll();
    Optional<Author> create(String name, String surname, Long countryId);
    Optional<Author> update(Long id, String name, String surname, Long countryId);
}
