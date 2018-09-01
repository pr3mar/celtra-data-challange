package com.celtra.challange.data.pr3mar.services.db;

import com.celtra.challange.data.pr3mar.dao.InteractionTypeDAO;
import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InternalServerExecption;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.InteractionTypeDTO;
import com.celtra.challange.data.pr3mar.models.entity.InteractionTypeEntity;
import com.celtra.challange.data.pr3mar.transformers.InteractionTypeTransformer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequestScoped
public class InteractionTypeDBService extends GenericDBService<InteractionTypeEntity, Long, InteractionTypeDTO> {

    @Inject
    protected InteractionTypeDAO dao;

    @Inject
    protected InteractionTypeTransformer transformer;

    @Override
    public InteractionTypeDTO findById(Long id) throws EntityNotFoundException, InvalidParameterException, InternalServerExecption {
        InteractionTypeEntity entity = dao.findById(id);
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<InteractionTypeDTO> findAll() throws EntityNotFoundException {
        List<InteractionTypeEntity> entities = dao.findAll();
        return transformer.transformToDTO(entities);
    }

    @Override
    public InteractionTypeDTO createNew(InteractionTypeDTO instance)
            throws  InvalidEntityException, EntityNotFoundException,
            InvalidParameterException, InternalServerExecption
    {
        InteractionTypeEntity entity = dao.createNew(
                transformer.transformToEntity(instance)
        );
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<InteractionTypeDTO> createNew(List<InteractionTypeDTO> dtos)
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        List<InteractionTypeDTO> created = new ArrayList<>();
        for(InteractionTypeDTO dto : dtos) {
            created.add(createNew(dto));
        }
        return created;
    }
}

