package com.example.summarizer.controller;

import com.example.summarizer.dto.SummarizeRequest;
import com.example.summarizer.service.GeminiService;
import com.example.summarizer.service.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SummarizeController {

    private final ScrapingService scrapingService;
    private final GeminiService geminiService;

    @Autowired
    public SummarizeController(ScrapingService scrapingService, GeminiService geminiService) {
        this.scrapingService = scrapingService;
        this.geminiService = geminiService;
    }

    //fixing the CORS
    @CrossOrigin(origins = "*")
    @PostMapping("/summarize")
    public Map<String, String> summarize(@RequestBody SummarizeRequest request) {
        
        System.out.println("Received request for URL: " + request.getUrl());

        // 1. Scraper
        String articleText = scrapingService.scrapeArticle(request.getUrl());

       
        if (articleText.startsWith("Error:")) {
            return Map.of("summary", articleText);
        }

        // 2. AI Summarization
        String summary = geminiService.summarize(articleText);

        System.out.println("Returning summary.");

        return Map.of("summary", summary);
    }
}