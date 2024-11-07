package com.anoshindanil.restfullregistrationproject.model;

import com.anoshindanil.restfullregistrationproject.model.dto.UserRegistrationDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AwaitingUser {
    private UserRegistrationDto userRegistrationDto;
    private String code;
    private LocalDateTime createdAt;

    public Boolean isCodeValid(int countOfMinutes) {
        return Duration.between(createdAt, LocalDateTime.now()).toMinutes() <= countOfMinutes;
    }
}
