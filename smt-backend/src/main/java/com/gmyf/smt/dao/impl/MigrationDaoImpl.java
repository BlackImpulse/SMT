package com.gmyf.smt.dao.impl;

import com.gmyf.smt.dao.AbstractDao;
import com.gmyf.smt.dao.api.MigrationDao;
import com.gmyf.smt.model.entity.Migration;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MigrationDaoImpl extends AbstractDao<Migration> implements MigrationDao {
    public MigrationDaoImpl() {
        super(Migration.class);
    }

    @Override
    public List<Migration> getMigrationsByUserId(long id) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Migration> criteriaQuery = criteriaBuilder.createQuery(Migration.class);
        Root<Migration> rootEntry = criteriaQuery.from(Migration.class);
        CriteriaQuery<Migration> list = criteriaQuery
                .select(rootEntry)
                .where(criteriaBuilder.equal(rootEntry.get("user").get("id"), id));

        TypedQuery<Migration> listQuery = sessionFactory.getCurrentSession().createQuery(list);

        return listQuery.getResultList();
    }
}
