package com.gmyf.smt.service.dto;

import java.util.Date;

public class UserDto {
    private long id;
    private String username;
    private String password;
    private Date registrationDate;

    public UserDto() {
    }

    public UserDto(long id, String userName, String password, Date registrationDate) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
