package com.example.urlshortener.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class URLShortModel {
    @Schema(title = "Short URL", example = "http://short.est/BTQyg", requiredMode = REQUIRED)
    @NotBlank(message = "Must not be null or empty string")
    @URL(protocol = "http", host = "short.est", message = "URL must belong to the domain: http://short.est/")
    private String shortURL;
}
