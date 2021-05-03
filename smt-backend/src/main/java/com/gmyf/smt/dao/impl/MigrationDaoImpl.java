package com.gmyf.smt.dao.impl;

import com.gmyf.smt.dao.AbstractDao;
import com.gmyf.smt.dao.api.MigrationDao;
import com.gmyf.smt.model.entity.Migration;
import org.springframework.stereotype.Repository;

@Repository
public class MigrationDaoImpl extends AbstractDao<Migration> implements MigrationDao {
    public MigrationDaoImpl() {
        super(Migration.class);
    }
}
