package com.gmyf.smt.payload;

import com.gmyf.smt.service.dto.MigrationTypeDto;
import com.gmyf.smt.service.dto.ServiceDto;
import com.gmyf.smt.service.dto.UserDto;

import java.util.List;

public class MigrationPayload {
    private List<String> names;
    private ServiceDto fromService;
    private ServiceDto toService;
    private String username;
    private MigrationTypeDto migrationType;

    public MigrationPayload() {
    }

    public MigrationPayload(List<String> names, ServiceDto fromService, ServiceDto toService, String username,
                            MigrationTypeDto migrationType) {
        this.names = names;
        this.fromService = fromService;
        this.toService = toService;
        this.username = username;
        this.migrationType = migrationType;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMigrationType(MigrationTypeDto migrationType) {
        this.migrationType = migrationType;
    }

    public MigrationTypeDto getMigrationType() {
        return migrationType;
    }
}
