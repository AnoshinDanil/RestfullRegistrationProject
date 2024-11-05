package com.anoshindanil.restfullregistrationproject.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotBlank
    @Size(min = 6, max = 50,message = "size login 6-50 ")
    private String username;

    @NotBlank
    @Email(message = "incorrect email")
    private String email;

    @NotBlank
    @Size(min=6, max=50, message = "password size must be from 6 to 60")
    @Pattern(regexp = ".*[A-Za-zА-Яа-я]*.]",message = "пароль должен содержать хотя бы 1 букву")
    private String password;

    @NotBlank
    @Size(min=6, max=50, message = "password size must be from 6 to 60")
    private String repeatPassword;
}
