package com.gmyf.smt.factory.api;

public interface ServiceApi {
    String getAuthUrl();
    String[] getTokens(String code);
    String[] getNewAccessToken(String refreshToken);
}
