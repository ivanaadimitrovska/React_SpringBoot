package com.example.lab_emt.config;

import com.example.lab_emt.model.User;
import com.example.lab_emt.model.enumerations.Category;
import com.example.lab_emt.model.enumerations.Role;
import com.example.lab_emt.service.AuthorService;
import com.example.lab_emt.service.BookService;
import com.example.lab_emt.service.CountryService;
import com.example.lab_emt.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    public static final String LIBRARIAN = "librarian";

    private final UserService userService;

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(UserService userService, BookService bookService, AuthorService authorService, CountryService countryService) {
        this.userService = userService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }


    @PostConstruct
    public void initData() {
        User librarian = this.userService.create(LIBRARIAN, LIBRARIAN, Role.ROLE_LIBRARIAN);
        this.countryService.create("Russia", "Europe");
        this.authorService.create("Leo", "Tolstoy", countryService.listAll().get(0).getId());

        this.bookService.create_1("War and Peace", Category.HISTORY, authorService.listAll().get(0), 450);

        this.countryService.create("Germany", "Europe");
        this.authorService.create("Sebastian", "Fitzek", countryService.listAll().get(1).getId());

        this.bookService.create_1("Passenger 23", Category.THRILLER, authorService.listAll().get(1), 300);

        this.countryService.create("France", "Europe");
        this.authorService.create("Victor", "Hugo", countryService.listAll().get(2).getId());

        this.bookService.create_1("Les Misérables", Category.HISTORY, authorService.listAll().get(2), 950);
    }
}