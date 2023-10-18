package com.example.urlshortener.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class ViewController {
    @RequestMapping("/showForm")
    public String showForm() {
        return "initial-form";
    }

    @RequestMapping("/processForm")
    public String processForm(HttpServletRequest request, Model model) {
        String shortUrl = request.getParameter("shortURL");
        String originalURL = shortUrl;
        model.addAttribute("originalURL", originalURL);
        return "showOriginalURL";
    }
}
