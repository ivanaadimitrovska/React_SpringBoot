package com.example.lab_emt.model.exception;

public class AuthorNotFound extends RuntimeException{

    public AuthorNotFound(Long id) {
        super(String.format("Author with %d id is not found", id));
    }
}
