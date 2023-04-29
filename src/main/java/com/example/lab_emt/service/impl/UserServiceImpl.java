package com.example.lab_emt.service.impl;

import com.example.lab_emt.model.User;
import com.example.lab_emt.model.enumerations.Role;
import com.example.lab_emt.repository.UserRepository;
import com.example.lab_emt.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(String username, String password, Role role) {
        String encyptedpasswrod=this.passwordEncoder.encode(password);
        User user =new User(username, encyptedpasswrod, role);
        return userRepository.save(user);
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

}
