package com.anoshindanil.restfullregistrationproject.controller;

import com.anoshindanil.restfullregistrationproject.model.dto.UserRegistrationDto;
import com.anoshindanil.restfullregistrationproject.model.entity.User;
import com.anoshindanil.restfullregistrationproject.service.CodeGenerator;
import com.anoshindanil.restfullregistrationproject.service.EmailService;
import com.anoshindanil.restfullregistrationproject.service.RegistrationUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final RegistrationUserService registrationUserService;
    private final EmailService emailService;
    private final CodeGenerator codeGenerator;



    @Value(value = "${spring.mail.username}")
    private  String from;

    @PostMapping("/new")
    public ResponseEntity<String> registration (@Valid @RequestBody UserRegistrationDto userDto) {
        Boolean userNameExist = registrationUserService.isUserNameExist(userDto.getUsername());

        if (userNameExist) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        String code = codeGenerator.generateCode();

        registrationUserService.addAwaitingUser(userDto, code);

        emailService.sendEmail(
                userDto.getEmail(),
                from,
                "Подтвердите регистрацию",
                "Код подстверждения " + code
        );

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
    }

    @PostMapping("/confirm")
    public ResponseEntity<User> confirmUser(@RequestParam("email") String email,
                                            @RequestParam("code") String code) {
        User user = registrationUserService.confirmRegistration(email, code);
        return ResponseEntity.ok(user);
    }
}
