package com.example.urlshortener.util;

import com.example.urlshortener.config.ShortenerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomStringGenerator {
    private final ShortenerProperties properties;

    public String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        int alphabetLength = properties.getAlphabetLetters().length();
        for (int i = 0; i < properties.getGeneratedStringLength(); i++) {
            int index = random.nextInt(alphabetLength);
            char randomCharFromAlphabet = properties.getAlphabetLetters().charAt(index);
            stringBuilder.append(randomCharFromAlphabet);
        }

        return stringBuilder.toString();
    }
}
