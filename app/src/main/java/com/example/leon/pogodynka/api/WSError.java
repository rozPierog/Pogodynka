package com.example.leon.pogodynka.api;

/**
 * Created by Leon on 12-Dec-17.
 */

public class WSError {
    private long timestamp;
    private int status;
    private String error;
    private String exception;
    private String message;
    private String path;

    public long getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public static WSError getServerConnectionError(String msg){
        WSError error = new WSError();
        error.status = 500;
        error.message = msg;
        return error;

    }
}
