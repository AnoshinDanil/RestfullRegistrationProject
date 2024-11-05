package com.anoshindanil.restfullregistrationproject.service;

import com.anoshindanil.restfullregistrationproject.mappers.UserMapper;
import com.anoshindanil.restfullregistrationproject.model.AwaitingUser;
import com.anoshindanil.restfullregistrationproject.model.dto.UserRegistrationDto;
import com.anoshindanil.restfullregistrationproject.model.entity.User;
import com.anoshindanil.restfullregistrationproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrationUserServiceImpl implements RegistrationUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private Map<String, AwaitingUser> awaitingUserMap = new HashMap<>();


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
}
