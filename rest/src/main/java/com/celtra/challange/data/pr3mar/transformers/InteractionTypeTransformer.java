package com.celtra.challange.data.pr3mar.transformers;

import com.celtra.challange.data.pr3mar.models.dto.InteractionTypeDTO;
import com.celtra.challange.data.pr3mar.models.entity.InteractionTypeEntity;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class InteractionTypeTransformer extends GenericTransformer<InteractionTypeEntity, InteractionTypeDTO> {

    @Override
    public InteractionTypeDTO transformToDTO(InteractionTypeEntity entity) {
        if(entity == null) {
            return null;
        }
        return new InteractionTypeDTO(
                entity.getId(),
                entity.getType()
        );
    }

    @Override
    public List<InteractionTypeDTO> transformToDTO(List<InteractionTypeEntity> entities) {
        List<InteractionTypeDTO> dtos = new ArrayList<>();
        for (InteractionTypeEntity entity : entities) {
            if (entity != null) {
                dtos.add(transformToDTO(entity));
            }
        }
        return dtos;
    }

    @Override
    public InteractionTypeEntity transformToEntity(InteractionTypeDTO dto) {
        if(dto == null) {
            return null;
        }
        return new InteractionTypeEntity(
                dto.getId(),
                dto.getType()
        );
    }

    @Override
    public List<InteractionTypeEntity> transformToEntity(List<InteractionTypeDTO> dtos) {
        List<InteractionTypeEntity> entities = new ArrayList<>();
        for (InteractionTypeDTO dto : dtos) {
            if (dto != null) {
                entities.add(transformToEntity(dto));
            }
        }
        return entities;
    }
}
