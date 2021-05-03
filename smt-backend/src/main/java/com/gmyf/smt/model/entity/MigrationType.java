package com.gmyf.smt.model.entity;

import com.gmyf.smt.model.AbstractPersistableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "migration_type")
public class MigrationType extends AbstractPersistableEntity {
    @Column(name = "type")
    private String type;

    public MigrationType() {
    }

    public MigrationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
