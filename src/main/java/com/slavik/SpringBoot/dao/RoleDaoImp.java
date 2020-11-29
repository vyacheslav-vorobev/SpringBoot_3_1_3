package com.slavik.SpringBoot.dao;

import com.slavik.SpringBoot.model.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getOne(Long id) {
        return (Role) entityManager.createQuery("FROM Role where id = :Id")
                .setParameter("Id", id).getSingleResult();    }

}
