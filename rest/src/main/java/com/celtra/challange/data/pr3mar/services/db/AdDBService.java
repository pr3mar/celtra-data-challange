package com.celtra.challange.data.pr3mar.services.db;

import com.celtra.challange.data.pr3mar.dao.AdDAO;
import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InternalServerExecption;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.AdDTO;
import com.celtra.challange.data.pr3mar.models.entity.AdEntity;
import com.celtra.challange.data.pr3mar.transformers.AdTransformer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequestScoped
public class AdDBService extends GenericDBService<AdEntity, Long, AdDTO> {

    @Inject
    protected AdDAO dao;

    @Inject
    protected AdTransformer transformer;

    @Override
    public AdDTO findById(Long id) throws EntityNotFoundException, InvalidParameterException, InternalServerExecption {
        AdEntity entity = dao.findById(id);
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<AdDTO> findAll() throws EntityNotFoundException {
        List<AdEntity> entities = dao.findAll();
        return transformer.transformToDTO(entities);
    }

    @Override
    public AdDTO createNew(AdDTO instance)
            throws  InvalidEntityException, EntityNotFoundException,
            InvalidParameterException, InternalServerExecption
    {
        AdEntity entity = dao.createNew(
                transformer.transformToEntity(instance)
        );
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<AdDTO> createNew(List<AdDTO> dtos)
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        List<AdDTO> created = new ArrayList<>();
        for(AdDTO dto : dtos) {
            created.add(createNew(dto));
        }
        return created;
    }
}

