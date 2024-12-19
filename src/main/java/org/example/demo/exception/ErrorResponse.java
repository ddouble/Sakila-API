package org.example.demo.exception;

public class ErrorResponse {
    public final String message;
    public final long timestamp;

    public ErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
