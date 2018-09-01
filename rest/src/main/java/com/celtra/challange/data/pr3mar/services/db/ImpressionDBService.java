package com.celtra.challange.data.pr3mar.services.db;

import com.celtra.challange.data.pr3mar.dao.ImpressionDAO;
import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InternalServerExecption;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.ImpressionDTO;
import com.celtra.challange.data.pr3mar.models.entity.ImpressionEntity;
import com.celtra.challange.data.pr3mar.transformers.ImpressionTransformer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequestScoped
public class ImpressionDBService extends GenericDBService<ImpressionEntity, Long, ImpressionDTO> {

    @Inject
    protected ImpressionDAO dao;

    @Inject
    protected ImpressionTransformer transformer;

    @Override
    public ImpressionDTO findById(Long id) throws EntityNotFoundException, InvalidParameterException, InternalServerExecption {
        ImpressionEntity entity = dao.findById(id);
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<ImpressionDTO> findAll() throws EntityNotFoundException {
        List<ImpressionEntity> entities = dao.findAll();
        return transformer.transformToDTO(entities);
    }

    @Override
    public ImpressionDTO createNew(ImpressionDTO instance)
            throws  InvalidEntityException, EntityNotFoundException,
            InvalidParameterException, InternalServerExecption
    {
        ImpressionEntity entity = dao.createNew(
                transformer.transformToEntity(instance)
        );
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<ImpressionDTO> createNew(List<ImpressionDTO> dtos)
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        List<ImpressionDTO> created = new ArrayList<>();
        for(ImpressionDTO dto : dtos) {
            created.add(createNew(dto));
        }
        return created;
    }
}

