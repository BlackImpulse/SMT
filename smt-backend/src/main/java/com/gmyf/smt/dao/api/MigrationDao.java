package com.gmyf.smt.dao.api;

import com.gmyf.smt.model.entity.Migration;

import javax.persistence.NoResultException;
import java.util.List;

public interface MigrationDao extends GenericDao<Migration> {
    List<Migration> getMigrationsByUserId(long id) throws NoResultException;
}
