package com.example.urlshortener.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class URLOriginalModel {
    @Schema(name = "Original URL",
            example = "https://www.google.com/search?q=rajnikant&rlz=1C5CHFA_enIN961IN961",
            required = true)
    private String originalURL;
}
