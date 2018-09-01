package com.celtra.challange.data.pr3mar.transformers;

import com.celtra.challange.data.pr3mar.models.dto.ImpressionDTO;
import com.celtra.challange.data.pr3mar.models.entity.ImpressionEntity;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ImpressionTransformer extends GenericTransformer<ImpressionEntity, ImpressionDTO> {

    @Override
    public ImpressionDTO transformToDTO(ImpressionEntity entity) {
        if(entity == null) {
            return null;
        }
        return new ImpressionDTO(
                entity.getId(),
                entity.getCampaignId(),
                entity.getAdId(),
                entity.getUserId(),
                entity.getInteractionTypeId(),
                entity.getDateOccurred(),
                entity.getDateStored()
        );
    }

    @Override
    public List<ImpressionDTO> transformToDTO(List<ImpressionEntity> entities) {
        List<ImpressionDTO> dtos = new ArrayList<>();
        for (ImpressionEntity entity : entities) {
            if (entity != null) {
                dtos.add(transformToDTO(entity));
            }
        }
        return dtos;
    }

    @Override
    public ImpressionEntity transformToEntity(ImpressionDTO dto) {
        if(dto == null) {
            return null;
        }
        return new ImpressionEntity(
                dto.getId(),
                dto.getCampaignId(),
                dto.getAdId(),
                dto.getUserId(),
                dto.getInteractionTypeId(),
                dto.getDateOccurred()
        );
    }

    @Override
    public List<ImpressionEntity> transformToEntity(List<ImpressionDTO> dtos) {
        List<ImpressionEntity> entities = new ArrayList<>();
        for (ImpressionDTO dto : dtos) {
            if (dto != null) {
                entities.add(transformToEntity(dto));
            }
        }
        return entities;
    }
}
