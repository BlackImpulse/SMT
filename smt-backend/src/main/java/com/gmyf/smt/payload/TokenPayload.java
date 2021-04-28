package com.gmyf.smt.payload;

public class TokenPayload {
    private String token;
    private String url;

    public TokenPayload() {
    }

    public TokenPayload(String token, String url) {
        this.token = token;
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
