package com.gmyf.smt.dao.impl;

import com.gmyf.smt.dao.AbstractDao;
import com.gmyf.smt.dao.api.ServiceDao;
import com.gmyf.smt.model.entity.Service;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class ServiceDaoImpl extends AbstractDao<Service> implements ServiceDao {
    public ServiceDaoImpl() {
        super(Service.class);
    }

    @Override
    public Service getServiceByName(String name) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Service> criteriaQuery = criteriaBuilder.createQuery(Service.class);
        Root<Service> rootEntry = criteriaQuery.from(Service.class);

        criteriaQuery.where(criteriaBuilder.equal(rootEntry.get("service"), name));

        TypedQuery<Service> getServiceByName = sessionFactory.getCurrentSession().createQuery(criteriaQuery);

        return getServiceByName.getSingleResult();
    }
}
