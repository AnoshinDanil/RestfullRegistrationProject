package com.anoshindanil.restfullregistrationproject.service;

import com.anoshindanil.restfullregistrationproject.model.dto.UserRegistrationDto;
import com.anoshindanil.restfullregistrationproject.model.entity.User;

public interface RegistrationUserService {
    Boolean isUserNameExist (String username);
    Boolean isPasswordExist (String password);
    void deleteUser (User user);
    User saveUser (UserRegistrationDto userDto);
    void addAwaitingUser (UserRegistrationDto userDto, String code);
    User confirmRegistration (String email, String code);
}
