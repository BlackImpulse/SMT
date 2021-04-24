package com.gmyf.smt.dao.impl;

import com.gmyf.smt.dao.AbstractDao;
import com.gmyf.smt.dao.api.UserDao;
import com.gmyf.smt.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User getUserByUsername(String userName) throws NoResultException {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> rootEntry = criteriaQuery.from(User.class);

        criteriaQuery.where(criteriaBuilder.equal(rootEntry.get("username"), userName));

        TypedQuery<User> getAdminByLoginQuery = sessionFactory.getCurrentSession().createQuery(criteriaQuery);

        return getAdminByLoginQuery.getSingleResult();
    }
}
