package com.celtra.challange.data.pr3mar.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

    private long id;
    private String email;
    private Date dateStored;

    public UserDTO() {
    }

    public UserDTO(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public UserDTO(long id, String email, Date dateStored) {
        this.id = id;
        this.email = email;
        this.dateStored = dateStored;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateStored() {
        return dateStored;
    }

    public void setDateStored(Date dateStored) {
        this.dateStored = dateStored;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dateStored=" + dateStored +
                '}';
    }
}
