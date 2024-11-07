package com.anoshindanil.restfullregistrationproject.service;

import com.anoshindanil.restfullregistrationproject.mappers.UserMapper;
import com.anoshindanil.restfullregistrationproject.model.AwaitingUser;
import com.anoshindanil.restfullregistrationproject.model.dto.UserRegistrationDto;
import com.anoshindanil.restfullregistrationproject.model.entity.User;
import com.anoshindanil.restfullregistrationproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrationUserServiceImpl implements RegistrationUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Map<String, AwaitingUser> awaitingUserMap = new HashMap<>();


    @Override
    public Boolean isUserNameExist(String username) {
        return userRepository.isUserNameExists(username);
    }

    @Override
    public Boolean isPasswordExist(String password) {
        return userRepository.isPasswordExists(password);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User saveUser(UserRegistrationDto userDto) {
        User user = userMapper.toUser(userDto);
        return userRepository.save(user);
    }

    @Override
    public void addAwaitingUser(UserRegistrationDto userDto, String code) {
        AwaitingUser awaitingUser = new AwaitingUser(
                userDto,
                code,
                LocalDateTime.now()
        );

        awaitingUserMap.put(userDto.getEmail(), awaitingUser);
    }

    @Override
    public User confirmRegistration(String email, String code) {
        AwaitingUser awaitingUser = awaitingUserMap.get(email);

        if (awaitingUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or already confirmed.");
        }

       if (!awaitingUser.getCode().equals(code)) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid confirmation code.");
       }

        if (awaitingUser.isCodeValid(2)) {
            awaitingUserMap.remove(email);
            throw new ResponseStatusException(HttpStatus.GONE, "Confirmation code has expired.");
        }

        User user = userMapper.toUser(awaitingUser.getUserRegistrationDto());
        userRepository.save(user);

        awaitingUserMap.remove(email);

        return user;
    }
}
