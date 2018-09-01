package com.celtra.challange.data.pr3mar.dao;

import com.celtra.challange.data.pr3mar.models.entity.UserEntity;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class UserDAO extends GenericDAO<UserEntity, Long> {

    public UserDAO() {
        super(UserEntity.class);
    }

    @Override
    public List<UserEntity> findAll() {
        return em.createNamedQuery("User.findAll", UserEntity.class)
                .getResultList();
    }

    public long countAll() {
        return em.createNamedQuery("User.countAll", Long.class)
                .getSingleResult();
    }

    public List<Long> getIds() {
        return em.createNamedQuery("User.getIds", Long.class)
                .getResultList();
    }

    public Long getLatestId() {
        return em.createNamedQuery("User.getLastId", Long.class)
                .setMaxResults(1)
                .getSingleResult();
    }
}
