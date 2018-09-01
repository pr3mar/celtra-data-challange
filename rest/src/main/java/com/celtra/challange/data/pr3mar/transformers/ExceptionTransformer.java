package com.celtra.challange.data.pr3mar.transformers;

import com.celtra.challange.data.pr3mar.models.dto.ExceptionDTO;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ExceptionTransformer {

    public ExceptionDTO transformToDTO(Exception error, int status) {
        return new ExceptionDTO(
                status,
                error.getMessage()
        );
    }
}
