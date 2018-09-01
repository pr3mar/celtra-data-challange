package com.celtra.challange.data.pr3mar.services.db;

import com.celtra.challange.data.pr3mar.dao.UserDAO;
import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InternalServerExecption;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.UserDTO;
import com.celtra.challange.data.pr3mar.models.entity.UserEntity;
import com.celtra.challange.data.pr3mar.transformers.UserTransformer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequestScoped
public class UserDBService extends GenericDBService<UserEntity, Long, UserDTO> {

    @Inject
    protected UserDAO dao;

    @Inject
    protected UserTransformer transformer;

    @Override
    public UserDTO findById(Long id) throws EntityNotFoundException, InvalidParameterException, InternalServerExecption {
        UserEntity entity = dao.findById(id);
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<UserDTO> findAll() throws EntityNotFoundException {
        List<UserEntity> entities = dao.findAll();
        return transformer.transformToDTO(entities);
    }

    @Override
    public UserDTO createNew(UserDTO instance)
            throws  InvalidEntityException, EntityNotFoundException,
            InvalidParameterException, InternalServerExecption
    {
        UserEntity entity = dao.createNew(
                transformer.transformToEntity(instance)
        );
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<UserDTO> createNew(List<UserDTO> dtos)
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        List<UserDTO> created = new ArrayList<>();
        for(UserDTO dto : dtos) {
            created.add(createNew(dto));
        }
        return created;
    }
}

