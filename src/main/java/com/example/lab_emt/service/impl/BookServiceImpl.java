package com.example.lab_emt.service.impl;

import com.example.lab_emt.model.Author;
import com.example.lab_emt.model.Book;
import com.example.lab_emt.model.Country;
import com.example.lab_emt.model.enumerations.Category;
import com.example.lab_emt.model.exception.BookNotFound;
import com.example.lab_emt.repository.AuthorRepository;
import com.example.lab_emt.repository.BookRepository;
import com.example.lab_emt.repository.CountryRepository;
import com.example.lab_emt.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> create(String name, Category category, String authorName, String authorSurname, String countryName, Integer availableCopies) {
        //Author author=authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFound(authorId));
        Country country=new Country(countryName);
        countryRepository.save(country);
        Author author=new Author(authorName, authorSurname, country);
        authorRepository.save(author);
        return Optional.of(bookRepository.save(new Book(name, category, author, availableCopies)));
    }

    @Override
    public Optional<Book> create_1(String name, Category category, Author author, Integer availableCopies) {
        //Author author=authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFound(authorId));
        return Optional.of(bookRepository.save(new Book(name, category, author, availableCopies)));
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, String authorName, String authorSurname, String countryName, Integer availableCopies) {
        Book book=bookRepository.findById(id).orElseThrow(()->new BookNotFound(id));
        book.setName(name);
        book.setAvailableCopies(availableCopies);
        Country country=new Country(countryName);
        countryRepository.save(country);
        Author author=new Author(authorName, authorSurname, country);
        authorRepository.save(author);
        book.setAuthor(author);
        book.setCategory(category);

        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book rentedBook(Long id) {
        Book book=bookRepository.findById(id).orElseThrow(()->new BookNotFound(id));
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        return book;
    }
}
