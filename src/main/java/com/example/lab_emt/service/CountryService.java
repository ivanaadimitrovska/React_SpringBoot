package com.example.lab_emt.service;

import com.example.lab_emt.model.Country;

import java.util.List;

public interface CountryService {
    Country findById(Long id);
    List<Country> listAll();
    Country create(String name, String continent);
}
