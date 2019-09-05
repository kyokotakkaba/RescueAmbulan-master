package com.example.abyandafa.rescueambulance.model;

/**
 * Created by Abyan Dafa on 05/12/2017.
 */

public class APIResponse {

    private int status_code;
    private String message;
    private Object data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public APIResponse(int status_code, String message, Object data) {

        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }
}
