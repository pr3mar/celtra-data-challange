package com.celtra.challange.data.pr3mar.dao;

import com.celtra.challange.data.pr3mar.models.entity.InteractionTypeEntity;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class InteractionTypeDAO extends GenericDAO<InteractionTypeEntity, Long> {

    public InteractionTypeDAO() {
        super(InteractionTypeEntity.class);
    }

    @Override
    public List<InteractionTypeEntity> findAll() {
        return em.createNamedQuery("InteractionType.findAll", InteractionTypeEntity.class)
                .getResultList();
    }

    public long countAll() {
        return em.createNamedQuery("InteractionType.countAll", Long.class)
                .getSingleResult();
    }

    public List<Integer> getIds() {
        return em.createNamedQuery("InteractionType.getIds", Integer.class)
                .getResultList();
    }
}
