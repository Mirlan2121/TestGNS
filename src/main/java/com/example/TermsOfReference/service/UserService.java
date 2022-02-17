package com.example.TermsOfReference.service;


import com.example.TermsOfReference.entity.User;
import com.example.TermsOfReference.model.UserAuthModel;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User saveUsers(User users);
    User getById(Long id);
    User getByLogin(String login);
    User deleteId(Long id);
    String getAuthorizerToken(UserAuthModel userAuthModel);
}
