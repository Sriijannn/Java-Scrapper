package com.example.summarizer.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScrapingService {


    public String scrapeArticle(String url) {
        try {
            
            Document doc = Jsoup.connect(url).timeout(6000).get();
            
            
            String articleText = doc.select("p").text();
            
            return articleText;

        } catch (IOException e) {
       
            System.err.println("Error scraping URL: " + url);
            e.printStackTrace();
            return "Error: Could not scrape the article. The URL might be invalid or the site might be blocking scrapers.";
        }
    }
}