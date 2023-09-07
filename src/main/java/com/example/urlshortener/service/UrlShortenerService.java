package com.example.urlshortener.service;

import com.example.urlshortener.config.ShortenerProperties;
import com.example.urlshortener.model.URLOriginalModel;
import com.example.urlshortener.model.URLShortModel;
import com.example.urlshortener.model.exception.NoSuchURLFoundException;
import com.example.urlshortener.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private final  Map<String, String> URLMap = new ConcurrentHashMap<>();
    private final RandomStringGenerator randomGenerator;
    private final ShortenerProperties properties;

    public URLOriginalModel getOriginalURL(String shortURL) {
        String shortURLKey = shortURL.substring(properties.getShortUrlPrefix().length());
        String originalURLstring = URLMap.get(shortURLKey);
        if (originalURLstring == null) {
            throw new NoSuchURLFoundException(shortURL);
        }
        return URLOriginalModel.builder()
                .originalURL(originalURLstring)
                .build();
    }

    public URLShortModel createShortURL(URLOriginalModel originalURLModel) {
        String shortURL;
        while (true) {
            shortURL = randomGenerator.generate();

            if (!URLMap.containsKey(shortURL)) {
                synchronized (UrlShortenerService.class) {
                    if (!URLMap.containsKey(shortURL)) {
                        URLMap.put(shortURL, originalURLModel.getOriginalURL());
                        break;
                    }
                }
            }
        }
        return URLShortModel.builder()
                .shortURL(properties.getShortUrlPrefix() + shortURL)
                .build();
    }
}
