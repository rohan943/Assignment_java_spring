package com.example.demo.entity;
public class ApiResponse<T> {
    private String message;
    private T data;
    private boolean success;

    // Constructors
    public ApiResponse(String message, T data, boolean success) {
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public ApiResponse(String message, boolean success) {
        this(message, null, success);
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}
