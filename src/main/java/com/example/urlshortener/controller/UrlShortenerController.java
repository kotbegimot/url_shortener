package com.example.urlshortener.controller;

import com.example.urlshortener.model.exception.NoSuchURLFoundException;
import com.example.urlshortener.model.URLOriginalModel;
import com.example.urlshortener.model.URLShortModel;
import com.example.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(value = "/decode")
    @ResponseBody
    public URLOriginalModel getOriginalUrlRequestParam(@RequestParam(value = "shortenedURL") String shortUrl) {
        return shortenerService.getOriginalURL(shortUrl);
    }

    /**
     * GET request that returns original URL by the given short URL.
     *
     * @param shortUrl - short URL string
     * @return URLOriginalModel object
     */
    @GetMapping(value = "/decode/{shortUrl}")
    @ResponseBody
    public URLOriginalModel getOriginalUrlPathVariable(@PathVariable("shortUrl") String shortUrl) {
        return shortenerService.getOriginalURL(shortUrl);
    }

    /**
     * Creates short URL for the given original URL
     *
     * @param originalURLModel - original URL object
     * @return - URLOriginalModel object
     */
    @PostMapping("/encode")
    @ResponseStatus(value = HttpStatus.CREATED)
    public URLShortModel createShortURL(@RequestBody URLOriginalModel originalURLModel) {
        return shortenerService.createShortURL(originalURLModel);
    }

    /**
     * Custom exception, returns 404 if the requested URL does not exist.
     */
    @ExceptionHandler(NoSuchURLFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchURLFoundException(NoSuchURLFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
