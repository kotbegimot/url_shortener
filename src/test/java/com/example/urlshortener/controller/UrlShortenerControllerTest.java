package com.example.urlshortener.controller;

import com.example.urlshortener.model.URLOriginalModel;
import com.example.urlshortener.model.URLShortModel;
import com.example.urlshortener.service.UrlShortenerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UrlShortenerController.class)
@AutoConfigureMockMvc(addFilters = false)
class UrlShortenerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UrlShortenerService service;
    private URLOriginalModel originalURL;
    private URLShortModel shortURL;

    @BeforeEach
    void setUp() {
        originalURL = URLOriginalModel.builder()
                .originalURL("https://www.google.com/search?q=rajnikant&rlz=1C5CHFA_enIN961IN961&sxsrf=ALeKk01CgCbCOvDbH0-6362aa24dzoB4zg:1627480763291&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiD5Zim9oXyAhX-wTgGHTDvDMUQ_AUoAnoECAEQBA&biw=1792&bih=898\"")
                .build();
        shortURL = URLShortModel.builder()
                .shortURL("http://short.est/BTQyg")
                .build();
    }

    @Test
    @DisplayName("Should return original URL")
    @WithMockUser
    void getOriginalUrlRequestParamTest() throws Exception {
        when(service.getOriginalURL(shortURL.getShortURL())).thenReturn(originalURL);
        mockMvc.perform(get("/api/v1/urlshortener/decode")
                        .queryParam("shortenedURL", shortURL.getShortURL())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.originalURL", is(originalURL.getOriginalURL())));
        verify(service, times(1)).getOriginalURL(shortURL.getShortURL());
    }

    @Test
    @DisplayName("Should return original URL")
    @WithMockUser
    void getOriginalUrlPathVariableTest() throws Exception {
        when(service.getOriginalURL("BTQyg")).thenReturn(originalURL);
        mockMvc.perform(get("/api/v1/urlshortener/decode/" + "BTQyg")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.originalURL", is(originalURL.getOriginalURL())));
        verify(service, times(1)).getOriginalURL("BTQyg");
    }

    @Test
    @DisplayName("Should return short URL")
    @WithMockUser
    void createHotelTest() throws Exception {
        when(service.createShortURL(originalURL)).thenReturn(shortURL);
        mockMvc.perform(post("/api/v1/urlshortener/encode")
                        .content(toJsonString(originalURL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.shortURL", is(shortURL.getShortURL())));
        verify(service, times(1)).createShortURL(originalURL);
    }

    public static String toJsonString(final Object obj) throws RuntimeException {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
