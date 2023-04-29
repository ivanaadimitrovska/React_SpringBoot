package com.example.lab_emt.model.exception;

public class CountryNotFound extends RuntimeException{
    public CountryNotFound(Long id) {
        super(String.format("Country with %d id is not found", id));
    }

}
