package com.gmyf.smt.dao.impl;

import com.gmyf.smt.dao.AbstractDao;
import com.gmyf.smt.dao.api.TokenDao;
import com.gmyf.smt.model.entity.Token;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class  TokenDaoImpl extends AbstractDao<Token> implements TokenDao {
    public TokenDaoImpl() {
        super(Token.class);
    }

    @Override
    public Token getTokenByUserIdAndServiceId(long userId, long serviceId) throws NoResultException {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Token> criteriaQuery = criteriaBuilder.createQuery(Token.class);
        Root<Token> rootEntry = criteriaQuery.from(Token.class);

        criteriaQuery.where(criteriaBuilder.and(
                criteriaBuilder.equal(rootEntry.get("user").get("id"), userId),
                criteriaBuilder.equal(rootEntry.get("service").get("id"), serviceId)));

        TypedQuery<Token> getTokenByUserIdAndServiceIdQuery = sessionFactory.getCurrentSession().createQuery(criteriaQuery);

        return getTokenByUserIdAndServiceIdQuery.getSingleResult();
    }
}
