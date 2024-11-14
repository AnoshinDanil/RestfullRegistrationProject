package com.anoshindanil.restfullregistrationproject.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeGenerator {

    public String generateCode() {
        return String.valueOf(new Random().nextInt(1000,9999));
    }
}
