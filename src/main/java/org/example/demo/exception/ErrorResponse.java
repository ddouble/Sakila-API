package org.example.demo.exception;

import java.util.Map;

public class ErrorResponse {
    private String message;
    private Map<String, String> errors;
    private long timestamp;

    public ErrorResponse(String message, Map<String, String> errors, long timestamp) {
        this.message = message;
        this.errors = errors;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
