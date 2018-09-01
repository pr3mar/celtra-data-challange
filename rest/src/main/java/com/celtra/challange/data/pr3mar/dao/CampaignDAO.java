package com.celtra.challange.data.pr3mar.dao;

import com.celtra.challange.data.pr3mar.models.entity.AdEntity;
import com.celtra.challange.data.pr3mar.models.entity.CampaignEntity;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class CampaignDAO extends GenericDAO<CampaignEntity, Long> {

    public CampaignDAO() {
        super(CampaignEntity.class);
    }

    @Override
    public List<CampaignEntity> findAll() {
        return em.createNamedQuery("Campaign.findAll", CampaignEntity.class)
                .getResultList();
    }

    public Long getLatestId() {
        return em.createNamedQuery("Campaign.getLastId", Long.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    public Long getLastNIds(int max) {
        return em.createNamedQuery("Campaign.getIds", Long.class)
                .setMaxResults(max)
                .getSingleResult();
    }
}
