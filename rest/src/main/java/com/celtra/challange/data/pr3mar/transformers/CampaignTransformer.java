package com.celtra.challange.data.pr3mar.transformers;

import com.celtra.challange.data.pr3mar.models.dto.CampaignDTO;
import com.celtra.challange.data.pr3mar.models.entity.CampaignEntity;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CampaignTransformer extends GenericTransformer<CampaignEntity, CampaignDTO> {

    @Override
    public CampaignDTO transformToDTO(CampaignEntity entity) {
        if(entity == null) {
            return null;
        }
        return new CampaignDTO(
                entity.getId(),
                entity.getCampaignName(),
                entity.getDateStarted(),
                entity.getDateEnded(),
                entity.getDateStored()
        );
    }

    @Override
    public List<CampaignDTO> transformToDTO(List<CampaignEntity> entities) {
        List<CampaignDTO> dtos = new ArrayList<>();
        for (CampaignEntity entity : entities) {
            if (entity != null) {
                dtos.add(transformToDTO(entity));
            }
        }
        return dtos;
    }

    @Override
    public CampaignEntity transformToEntity(CampaignDTO dto) {
        if(dto == null) {
            return null;
        }
        return new CampaignEntity(
                dto.getId(),
                dto.getCampaignName(),
                dto.getDateStarted(),
                dto.getDateEnded()
        );
    }

    @Override
    public List<CampaignEntity> transformToEntity(List<CampaignDTO> dtos) {
        List<CampaignEntity> entities = new ArrayList<>();
        for (CampaignDTO dto : dtos) {
            if (dto != null) {
                entities.add(transformToEntity(dto));
            }
        }
        return entities;
    }
}
