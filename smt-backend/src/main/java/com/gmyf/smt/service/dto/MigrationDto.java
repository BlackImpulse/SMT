package com.gmyf.smt.service.dto;

import java.sql.Date;

public class MigrationDto {
    private long id;
    private UserDto user;
    private Date date;
    private ServiceDto fromService;
    private ServiceDto toService;
    private MigrationTypeDto migrationTypeDto;
    private int itemsCount;

    public MigrationDto() {
    }

    public MigrationDto(long id, UserDto user, Date date, ServiceDto fromService, ServiceDto toService, MigrationTypeDto migrationTypeDto, int itemsCount) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.fromService = fromService;
        this.toService = toService;
        this.migrationTypeDto = migrationTypeDto;
        this.itemsCount = itemsCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ServiceDto getFromService() {
        return fromService;
    }

    public void setFromService(ServiceDto fromService) {
        this.fromService = fromService;
    }

    public ServiceDto getToService() {
        return toService;
    }

    public void setToService(ServiceDto toService) {
        this.toService = toService;
    }

    public MigrationTypeDto getMigrationTypeDto() {
        return migrationTypeDto;
    }

    public void setMigrationTypeDto(MigrationTypeDto migrationTypeDto) {
        this.migrationTypeDto = migrationTypeDto;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }
}
