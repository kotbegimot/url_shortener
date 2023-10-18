package com.example.urlshortener.controller;

import com.example.urlshortener.model.URLOriginalModel;
import com.example.urlshortener.model.URLShortModel;
import com.example.urlshortener.service.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/urlshortener")
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerService shortenerService;

    /**
     * GET request that returns original URL by the given short URL.
     *
     * @param shortUrl - short URL string
     * @return URLOriginalModel object
     */
    @Operation(summary = "Get original URL by short URL", description = "Returns original URL by the given short URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Original URL is found"),
            @ApiResponse(responseCode = "404", description = "Not found - requested short URL does not found")
    })
    @GetMapping(value = "/decode")
    @ResponseBody
    public URLOriginalModel getOriginalUrlRequestParam(@RequestParam(value = "shortenedURL")
                                                       @Parameter(name = "shortenedURL",
                                                               description = "Short URL string",
                                                               example = "http://short.est/BTQyg")
                                                       String shortUrl) {
        return shortenerService.getOriginalURL(shortUrl);
    }

    /**
     * Creates short URL for the given original URL
     *
     * @param originalURLModel - original URL object
     * @return - URLOriginalModel object
     */
    @Operation(summary = "Returns short URL by original URL",
            description = "Generates short URL with given original URL and return short URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Short URL successfully generated"),
    })
    @PostMapping("/encode")
    @ResponseStatus(value = HttpStatus.CREATED)
    public URLShortModel createShortURL(@RequestBody URLOriginalModel originalURLModel) {
        return shortenerService.createShortURL(originalURLModel);
    }

    /**
     @GetMapping(value = "/decode/{shortUrl}")
     @ResponseBody public URLOriginalModel getOriginalUrlPathVariable(@PathVariable("shortUrl") String shortUrl) {
     return shortenerService.getOriginalURL(shortUrl);
     }
     */
}
