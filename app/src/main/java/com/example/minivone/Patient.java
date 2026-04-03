package com.example.minivone;

public class Patient {
    public String name;
    public String prediction;
    public String confidence;
    public int imageResId;

    public Patient(String name, String prediction, String confidence, int imageResId) {
        this.name = name;
        this.prediction = prediction;
        this.confidence = confidence;
        this.imageResId = imageResId;
    }
}