package com.example.summarizer.dto;

import java.util.List;

public class Part {
    private String text;

    public Part() {}
    
    public Part(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}