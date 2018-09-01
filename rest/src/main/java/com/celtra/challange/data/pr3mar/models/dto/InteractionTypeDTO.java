package com.celtra.challange.data.pr3mar.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InteractionTypeDTO {

    private int id;
    private String type;

    public InteractionTypeDTO() {
    }

    public InteractionTypeDTO(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "InteractionTypeDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
