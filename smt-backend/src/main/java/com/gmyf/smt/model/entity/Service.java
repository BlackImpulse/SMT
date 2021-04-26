package com.gmyf.smt.model.entity;

import com.gmyf.smt.model.AbstractPersistableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service extends AbstractPersistableEntity {
    @Column(name = "service")
    private String service;

    public Service() {
    }

    public Service(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
