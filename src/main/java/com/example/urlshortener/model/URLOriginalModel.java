package com.example.urlshortener.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class URLOriginalModel {
    @Schema(title = "Original URL",
            example = "https://www.google.com/search?q=rajnikant&rlz=1C5CHFA_enIN961IN961",
            requiredMode = REQUIRED)
    @NotBlank(message = "is required")
    private String originalURL;
}
