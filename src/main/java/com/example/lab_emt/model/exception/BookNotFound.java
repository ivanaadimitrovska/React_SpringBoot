package com.example.lab_emt.model.exception;

public class BookNotFound extends RuntimeException{
    public BookNotFound(Long id) {
        super(String.format("Book with %d id is not found", id));
    }
}
