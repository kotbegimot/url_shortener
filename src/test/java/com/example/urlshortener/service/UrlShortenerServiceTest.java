package com.example.urlshortener.service;

import com.example.urlshortener.config.ShortenerProperties;
import com.example.urlshortener.model.URLOriginalModel;
import com.example.urlshortener.model.URLShortModel;
import com.example.urlshortener.model.exception.NoSuchURLFoundException;
import com.example.urlshortener.util.RandomStringGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UrlShortenerServiceTest {
    private final RandomStringGenerator randomGenerator = mock(RandomStringGenerator.class);
    private final ShortenerProperties properties = mock(ShortenerProperties.class);

    private UrlShortenerService service;
    private URLOriginalModel originalURL;
    private URLShortModel shortURL;

    @BeforeEach
    void setUp() {
        service = new UrlShortenerService(randomGenerator, properties);
        originalURL = URLOriginalModel.builder()
                .originalURL("https://www.google.com/search?q=rajnikant&rlz=1C5CHFA_enIN961IN961&sxsrf=ALeKk01CgCbCOvDbH0-6362aa24dzoB4zg:1627480763291&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiD5Zim9oXyAhX-wTgGHTDvDMUQ_AUoAnoECAEQBA&biw=1792&bih=898\"")
                .build();
        shortURL = URLShortModel.builder()
                .shortURL("http://short.est/BTQyg")
                .build();
    }

    @Test
    @DisplayName("Service should return short URL model")
    void createShortURLTest() {
        when(randomGenerator.generate()).thenReturn("BTQyg");
        when(properties.getShortUrlPrefix()).thenReturn("http://short.est/");

        Assertions.assertEquals(shortURL, service.createShortURL(originalURL));

        verify(randomGenerator, times(1)).generate();
        verify(properties, times(1)).getShortUrlPrefix();
    }

    @Test
    @DisplayName("Service should throw NoSuchURLFoundException")
    void getOriginalURLThrowsExceptionTest() {
        when(properties.getShortUrlPrefix()).thenReturn("http://short.est/");

        Assertions.assertThrows(NoSuchURLFoundException.class, () -> service.getOriginalURL(shortURL.getShortURL()));

        verify(properties, times(1)).getShortUrlPrefix();
    }

    @Test
    @DisplayName("Service should return original URL model")
    void getOriginalURLTest() {
        when(randomGenerator.generate()).thenReturn("BTQyg");
        when(properties.getShortUrlPrefix()).thenReturn("http://short.est/");
        service.createShortURL(originalURL);

        Assertions.assertEquals(originalURL, service.getOriginalURL(shortURL.getShortURL()));

        verify(randomGenerator, times(1)).generate();
        verify(properties, times(2)).getShortUrlPrefix();
    }
}
