package com.gmyf.smt.service.dto;

public class TokenDto {
    private long id;
    private UserDto user;
    private ServiceDto service;
    private String accessToken;
    private String refreshToken;

    public TokenDto() {
    }

    public TokenDto(long id, UserDto user, ServiceDto service, String accessToken, String refreshToken) {
        this.id = id;
        this.user = user;
        this.service = service;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
