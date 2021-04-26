package com.gmyf.smt.payload;

import com.gmyf.smt.service.dto.TokenDto;

public class TokenPayload {
    private TokenDto token;
    private String url;

    public TokenPayload() {
    }

    public TokenPayload(TokenDto token, String url) {
        this.token = token;
        this.url = url;
    }

    public TokenDto getToken() {
        return token;
    }

    public void setToken(TokenDto token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
