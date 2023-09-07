package com.example.urlshortener.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "shortener")
@Getter
@Setter
@Validated
public class ShortenerProperties {
    @NotNull(message = "Shortener property \"shortUrlPrefix\" cannot be null: check application.yml")
    String shortUrlPrefix;
    @NotNull(message = "Shortener property \"generatedStringLength\" cannot be null: check application.yml")
    Integer generatedStringLength;
    @NotNull(message = "Shortener property \"alphabetLetters\" cannot be null: check application.yml")
    String alphabetLetters;
}
