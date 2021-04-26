package com.gmyf.smt.service.dto;

public class ServiceDto {
    private long id;
    private String service;

    public ServiceDto() {
    }

    public ServiceDto(long id, String service) {
        this.id = id;
        this.service = service;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
