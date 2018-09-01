package com.celtra.challange.data.pr3mar.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDTO implements Serializable {
    private int status;
    private String message;

    public ExceptionDTO() {
    }

    public ExceptionDTO(int status) {
        this.status = status;
    }

    public ExceptionDTO(String message) {
        this.message = message;
    }

    public ExceptionDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
