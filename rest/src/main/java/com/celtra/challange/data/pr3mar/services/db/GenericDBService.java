package com.celtra.challange.data.pr3mar.services.db;

import com.celtra.challange.data.pr3mar.exceptions.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class GenericDBService<E, PK, DTO> {

    public DTO findById(PK id)
            throws  EntityNotFoundException, InvalidParameterException,
                    InternalServerExecption
    {
        return null;
    }

    public List<DTO> findAll()
            throws EntityNotFoundException
    {
        return null;
    }

    public DTO createNew(DTO instance)
            throws  InvalidEntityException, EntityNotFoundException,
                    InvalidParameterException, InternalServerExecption
    {
        return null;
    }

    public List<DTO> createNew(List<DTO> dtos)
            throws  InternalServerExecption, InvalidEntityException,
            InvalidParameterException, EntityNotFoundException
    {
        return null;
    }

    public DTO updateById(PK id, DTO instance)
            throws  OperationNotAllowedException, EntityNotFoundException,
                    InvalidEntityException, InvalidParameterException, InternalServerExecption
    {
        throw new OperationNotAllowedException();
    }

    public boolean deleteById(PK id)
            throws OperationNotAllowedException
    {
        throw new OperationNotAllowedException();
    }

}
