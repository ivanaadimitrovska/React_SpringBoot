package com.example.lab_emt.service;

import com.example.lab_emt.model.User;
import com.example.lab_emt.model.enumerations.Role;

import java.util.List;

public interface UserService {
    List<User> listAll();
    User create(String username, String password, Role role);
}
