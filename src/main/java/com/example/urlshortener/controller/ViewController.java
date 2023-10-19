package com.example.urlshortener.controller;

import com.example.urlshortener.model.URLOriginalModel;
import com.example.urlshortener.model.URLShortModel;
import com.example.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final UrlShortenerService shortenerService;
    @GetMapping("/showForm")
    public String showForm(Model theModel) {
        theModel.addAttribute("shortUrlModel", new URLShortModel());
        theModel.addAttribute("originalUrlModel", new URLOriginalModel());
        return "initial-form";
    }

    @GetMapping("/processForm")
    public String processForm(@ModelAttribute("shortUrlModel") URLShortModel shortUrlModel,
                              @ModelAttribute("originalUrlModel") URLOriginalModel originalUrlModel) {
        originalUrlModel.setOriginalURL(shortenerService.getOriginalURL(shortUrlModel.getShortURL()).getOriginalURL());
        return "showOriginalURL";
    }
}
