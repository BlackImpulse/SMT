package com.gmyf.smt.dao.api;

import com.gmyf.smt.model.entity.Token;

import javax.persistence.NoResultException;

public interface TokenDao extends GenericDao<Token> {
    Token getTokenByUserIdAndServiceId(long userId, long serviceId) throws NoResultException;
}
