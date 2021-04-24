package com.gmyf.smt.dao.api;

import com.gmyf.smt.model.entity.User;

import javax.persistence.NoResultException;

public interface UserDao extends GenericDao<User>{
    User getUserByUsername(String userName) throws NoResultException;
}
