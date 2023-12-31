package com.example.urlshortener.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "shortener")
@Getter
@Setter
@Validated
public class ShortenerProperties {
    @NotNull(message = "Shortener property \"shortUrlPrefix\" cannot be null: check application.yml")
    String shortUrlPrefix;
    @NotNull(message = "Shortener property \"generatedStringLength\" cannot be null: check application.yml")
    @Min(value = 5, message = "Shortener property \"generatedStringLength\" should be greater then equal to 5")
    Integer generatedStringLength;
    @NotNull(message = "Shortener property \"alphabetLetters\" cannot be null: check application.yml")
    @Length(min = 10, message = "Shortener property \"alphabetLetters\" size should be greater then equal to 10")
    String alphabetLetters;
}

