package com.anoshindanil.restfullregistrationproject.service;


import com.anoshindanil.restfullregistrationproject.model.entity.User;

public interface RegistrationUserService {
    Boolean isUserNameExist (String username);
    Boolean isPasswordExist (String password);

    void saveAwaitingUser (User user);
    void deleteAwaitingUser (User user);

    User saveUser (User user);
}
