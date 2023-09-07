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
public class URLShortModel {
    @Schema(name = "Short URL", example = "http://short.est/BTQyg", required = true)
    private String shortURL;
}
