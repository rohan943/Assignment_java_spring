package com.example.demo.entity;

public class SuccessResponse {

    private String message;

    // Constructor
    public SuccessResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }
}
