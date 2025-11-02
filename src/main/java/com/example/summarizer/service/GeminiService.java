package com.example.summarizer.service;

import com.example.summarizer.dto.Content;
import com.example.summarizer.dto.GeminiRequest;
import com.example.summarizer.dto.GeminiResponse;
import com.example.summarizer.dto.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String geminiApiKey;
    private final RestTemplate restTemplate;

    
    @Autowired
    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String summarize(String text) {
        //Gemini API endpoint.
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-preview-09-2025:generateContent?key=" + geminiApiKey;


        // Promt
        String prompt = "Summarize the following article text as briefly as possible so that it can be read easiliy, bullet points would be appreciated: \n\n" + text;

        Part part = new Part(prompt);
        Content content = new Content(List.of(part));
        GeminiRequest request = new GeminiRequest(List.of(content));

        try {
            
            GeminiResponse response = restTemplate.postForObject(apiUrl, request, GeminiResponse.class);

            if (response != null && response.getCandidates() != null && !response.getCandidates().isEmpty()) {
                
                return response.getCandidates().get(0).getContent().getParts().get(0).getText();
            } else {
                return "Error: Could not get a valid summary from Gemini.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Failed to call Gemini API. Check your API key and network connection.";
        }
    }
}