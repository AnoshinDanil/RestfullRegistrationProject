package com.anoshindanil.restfullregistrationproject.service;

import com.anoshindanil.restfullregistrationproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationUserServiceImpl implements RegistrationUserService {
    private final UserRepository userRepository;


}
