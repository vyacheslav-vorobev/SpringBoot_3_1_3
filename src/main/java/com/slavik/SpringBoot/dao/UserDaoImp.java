package com.slavik.SpringBoot.dao;

import com.slavik.SpringBoot.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("from User").getResultList();
    }
    @Override
    public User getUser(Long id) {
        return (User) entityManager.createQuery("FROM User where id = :Id")
                .setParameter("Id", id).getSingleResult();
    }
    @Override
    public User getUserByLogin(String login) {
        return (User) entityManager.createQuery("FROM User where login = :login")
                .setParameter("login", login).getSingleResult();
    }
    @Override
    public void remove(Long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id).executeUpdate();
    }
    @Override
    public void upDate(Long id, User user) {
        entityManager.merge(user);
    }
}
