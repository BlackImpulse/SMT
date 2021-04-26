package com.gmyf.smt.model.entity;

import com.gmyf.smt.model.AbstractPersistableEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;


@Entity
@Table(name = "token")
public class Token extends AbstractPersistableEntity {
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token")
    private String token;

    @ManyToOne(targetEntity = Service.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    public Token() {
    }

    public Token(User user, String token, Service service) {
        this.user = user;
        this.token = token;
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
