package com.gmyf.smt.dao.api;

import com.gmyf.smt.model.entity.User;

public interface UserDao extends GenericDao<User>{
    User getUserByUserName(String userName);
}
