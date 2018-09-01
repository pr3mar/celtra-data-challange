package com.celtra.challange.data.pr3mar.transformers;

import com.celtra.challange.data.pr3mar.models.dto.AdDTO;
import com.celtra.challange.data.pr3mar.models.entity.AdEntity;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class AdTransformer extends GenericTransformer<AdEntity, AdDTO> {

    @Override
    public AdDTO transformToDTO(AdEntity entity) {
        if(entity == null) {
            return null;
        }
        return new AdDTO(
                entity.getId(),
                entity.getAdName(),
                entity.getCampaignId(),
                entity.getDateStored()
        );
    }

    @Override
    public List<AdDTO> transformToDTO(List<AdEntity> entities) {
        List<AdDTO> dtos = new ArrayList<>();
        for (AdEntity entity : entities) {
            if (entity != null) {
                dtos.add(transformToDTO(entity));
            }
        }
        return dtos;
    }

    @Override
    public AdEntity transformToEntity(AdDTO dto) {
        if(dto == null) {
            return null;
        }
        return new AdEntity(
                dto.getId(),
                dto.getAdName(),
                dto.getCampaignId()
        );
    }

    @Override
    public List<AdEntity> transformToEntity(List<AdDTO> dtos) {
        List<AdEntity> entities = new ArrayList<>();
        for (AdDTO dto : dtos) {
            if (dto != null) {
                entities.add(transformToEntity(dto));
            }
        }
        return entities;
    }
}
