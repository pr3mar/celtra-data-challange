package com.celtra.challange.data.pr3mar.services.db;

import com.celtra.challange.data.pr3mar.dao.CampaignDAO;
import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InternalServerExecption;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.CampaignDTO;
import com.celtra.challange.data.pr3mar.models.entity.CampaignEntity;
import com.celtra.challange.data.pr3mar.transformers.CampaignTransformer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequestScoped
public class CampaignDBService extends GenericDBService<CampaignEntity, Long, CampaignDTO> {

    @Inject
    protected CampaignDAO dao;

    @Inject
    protected CampaignTransformer transformer;

    @Override
    public CampaignDTO findById(Long id) throws EntityNotFoundException, InvalidParameterException, InternalServerExecption {
        CampaignEntity entity = dao.findById(id);
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<CampaignDTO> findAll() throws EntityNotFoundException {
        List<CampaignEntity> entities = dao.findAll();
        return transformer.transformToDTO(entities);
    }

    @Override
    public CampaignDTO createNew(CampaignDTO instance)
            throws  InvalidEntityException, EntityNotFoundException,
            InvalidParameterException, InternalServerExecption
    {
        CampaignEntity entity = dao.createNew(
                transformer.transformToEntity(instance)
        );
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<CampaignDTO> createNew(List<CampaignDTO> dtos)
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        List<CampaignDTO> created = new ArrayList<>();
        for(CampaignDTO dto : dtos) {
            created.add(createNew(dto));
        }
        return created;
    }
}

