package com.gmyf.smt.dao.api;

import com.gmyf.smt.model.entity.Service;

public interface ServiceDao extends GenericDao<Service> {
    Service getServiceByName(String name);
}
