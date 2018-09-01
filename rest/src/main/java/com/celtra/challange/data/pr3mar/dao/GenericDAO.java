package com.celtra.challange.data.pr3mar.dao;

import com.celtra.challange.data.pr3mar.exceptions.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericDAO<E, PK> {

    @PersistenceContext
    protected EntityManager em;

    protected Class<? extends E> entityClass;

    public GenericDAO(){
    }

    public GenericDAO(Class<? extends E> entityClass) {
        this.entityClass = entityClass;
    }

    public E findById(PK id)
            throws  InvalidParameterException,
                    EntityNotFoundException,
                    InternalServerExecption
    {
        E entity;
        try {
            entity = em.find(entityClass, id);
        } catch (javax.persistence.EntityNotFoundException | NoResultException e) {
            throw new EntityNotFoundException("Entity not found");
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerExecption("Something happened " + e.getMessage());
        }
        if(entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

    public List<E> findAll()
            throws EntityNotFoundException, OperationNotAllowedException
    {
        throw new OperationNotAllowedException();
    }

    @Transactional
    public E createNew(E instance)
            throws  EntityNotFoundException,
                    InvalidEntityException,
                    InvalidParameterException,
                    InternalServerExecption
    {
        if(instance == null) {
            throw new InvalidEntityException();
        }
        em.persist(instance);
        return instance;
    }

    @Transactional
    public E update(PK id, E instance)
            throws  EntityNotFoundException,
                    InvalidParameterException,
                    InvalidEntityException,
                    OperationNotAllowedException,
                    InternalServerExecption
    {
        throw new OperationNotAllowedException();
    }

    @Transactional
    public boolean deleteById(PK id)
            throws  EntityNotFoundException,
                    OperationNotAllowedException,
                    InvalidParameterException,
                    InternalServerExecption
    {
        throw new OperationNotAllowedException();
    }
}
