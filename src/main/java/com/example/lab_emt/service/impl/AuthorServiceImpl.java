package com.example.lab_emt.service.impl;

import com.example.lab_emt.model.Author;
import com.example.lab_emt.model.Country;
import com.example.lab_emt.model.exception.AuthorNotFound;
import com.example.lab_emt.model.exception.CountryNotFound;
import com.example.lab_emt.repository.AuthorRepository;
import com.example.lab_emt.repository.CountryRepository;
import com.example.lab_emt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> create(String name, String surname, Long countryId) {
        Country country=countryRepository.findById(countryId).orElseThrow(()-> new CountryNotFound(countryId));
        return Optional.of(authorRepository.save(new Author(name, surname, country)));
    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {
        Author author=authorRepository.findById(id).orElseThrow(()->new AuthorNotFound(id));
        Country country=countryRepository.findById(countryId).orElseThrow(()-> new CountryNotFound(countryId));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);

        authorRepository.save(author);
        return Optional.of(author);
    }
}
