package com.example.TermsOfReference.service.impl;

import com.example.TermsOfReference.entity.User;
import com.example.TermsOfReference.model.UserAuthModel;
import com.example.TermsOfReference.repository.UserRepository;
import com.example.TermsOfReference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUsers(User users) {
        return userRepository.save(users);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public User deleteId(Long id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
    @Override
    public String getAuthorizerToken(UserAuthModel userAuthModel) {
        User user = userRepository.findByLogin(userAuthModel.getLogin()).orElseThrow(
                () -> new IllegalArgumentException("Неверный логин или пароль"));
        boolean isPasswordMatches = passwordEncoder.matches(userAuthModel.getPassword(), user.getPassword());
        if (!isPasswordMatches) {
            throw new IllegalArgumentException("Неверный Логин или Пароль");
        }
        String userNamePasswordPair = userAuthModel.getLogin() + ": " + userAuthModel.getPassword();
        return "Basic " + new String(Base64.getEncoder().encode(userNamePasswordPair.getBytes()));
    }
}
