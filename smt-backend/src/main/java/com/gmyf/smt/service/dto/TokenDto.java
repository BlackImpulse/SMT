package com.gmyf.smt.service.dto;

public class TokenDto {
    private long id;
    private UserDto user;
    private ServiceDto service;
    private String token;

    public TokenDto() {
    }

    public TokenDto(long id, UserDto user, ServiceDto service, String token) {
        this.id = id;
        this.user = user;
        this.service = service;
        this.token = token;
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

    public ServiceDto getService() {
        return service;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
