package com.gmyf.smt.dao.api;

import com.gmyf.smt.model.entity.Token;

import javax.persistence.NoResultException;
import java.util.List;

public interface TokenDao extends GenericDao<Token> {
    Token getTokenByUserIdAndServiceId(long userId, long serviceId) throws NoResultException;
    List<Token> getTokensByUserId(long userId) throws NoResultException;
}
