package com.example.lab_emt.service.impl;

import com.example.lab_emt.model.Country;
import com.example.lab_emt.model.exception.CountryNotFound;
import com.example.lab_emt.repository.CountryRepository;
import com.example.lab_emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(()->new CountryNotFound(id));
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country create(String name, String continent) {
        return countryRepository.save(new Country(name, continent));
    }
}
