package com.example.summarizer.dto;


public class SummarizeRequest {
    
    private String url;
    private String apiKey;


    public SummarizeRequest() {
      
    }

    public String getUrl() {
        return this.url;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}