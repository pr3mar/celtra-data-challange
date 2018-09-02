package com.celtra.challange.data.pr3mar.dao;

import com.celtra.challange.data.pr3mar.models.entity.ImpressionEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import java.util.List;

@RequestScoped
public class ImpressionDAO extends GenericDAO<ImpressionEntity, Long> {

    public ImpressionDAO() {
        super(ImpressionEntity.class);
    }

    @Override
    public List<ImpressionEntity> findAll() {
        return em.createNamedQuery("Impression.findAll", ImpressionEntity.class)
                .getResultList();
    }

    public Long getLatestId() {
        try {
            return em.createNamedQuery("Impression.getLastId", Long.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return -1L;
        }
    }

    public Long getLastNIds(int max) {
        return em.createNamedQuery("Impression.getIds", Long.class)
                .setMaxResults(max)
                .getSingleResult();
    }
}
