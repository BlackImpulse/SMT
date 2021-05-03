package com.gmyf.smt.model.entity;

import com.gmyf.smt.model.AbstractPersistableEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import java.sql.Date;

@Entity
@Table(name = "migration")
public class Migration extends AbstractPersistableEntity {
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date date;

    @ManyToOne(targetEntity = Service.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_service_id")
    private Service fromService;

    @ManyToOne(targetEntity = Service.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_service_id")
    private Service toService;

    @ManyToOne(targetEntity = MigrationType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "migration_type_id")
    private MigrationType migrationType;

    @Column(name = "items_count")
    private int itemsCount;

    public Migration() {
    }

    public Migration(User user, Date date, Service fromService, Service toService, MigrationType migrationType, int itemsCount) {
        this.user = user;
        this.date = date;
        this.fromService = fromService;
        this.toService = toService;
        this.migrationType = migrationType;
        this.itemsCount = itemsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Service getFromService() {
        return fromService;
    }

    public void setFromService(Service fromService) {
        this.fromService = fromService;
    }

    public Service getToService() {
        return toService;
    }

    public void setToService(Service toService) {
        this.toService = toService;
    }

    public MigrationType getMigrationType() {
        return migrationType;
    }

    public void setMigrationType(MigrationType migrationType) {
        this.migrationType = migrationType;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }
}
