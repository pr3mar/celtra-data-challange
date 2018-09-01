package com.celtra.challange.data.pr3mar.dao;

import com.celtra.challange.data.pr3mar.models.entity.AdEntity;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class AdDAO extends GenericDAO<AdEntity, Long> {

    public AdDAO() {
        super(AdEntity.class);
    }

    @Override
    public List<AdEntity> findAll() {
        return em.createNamedQuery("Ad.findAll", AdEntity.class)
                    .getResultList();
    }

    public long countAll() {
        return em.createNamedQuery("Ad.countAll", Long.class)
                .getSingleResult();
    }

    public Long getLatestId() {
        return em.createNamedQuery("Ad.getLastId", Long.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    public Long getLastNIds(int max) {
        return em.createNamedQuery("Ad.getIds", Long.class)
                .setMaxResults(max)
                .getSingleResult();
    }
}
