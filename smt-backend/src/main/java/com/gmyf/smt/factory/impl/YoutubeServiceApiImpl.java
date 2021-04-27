package com.gmyf.smt.factory.impl;

import com.gmyf.smt.factory.api.ServiceApi;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTubeScopes;

import java.io.IOException;
import java.util.Collections;

public class YoutubeServiceApiImpl implements ServiceApi {
    private final String clientId = "1029476631579-00q1r5o50t6p45bknimoff51gk69f5pr.apps.googleusercontent.com";
    private final String clientSecret = "EcC6YmJkgu6z7hSIzK2BsIdy";
    private final GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), GsonFactory.getDefaultInstance(), clientId, clientSecret,
            Collections.singletonList(YouTubeScopes.YOUTUBE))
            .setAccessType("online")
            .build();

    @Override
    public String getAuthUrl() {
        return googleAuthorizationCodeFlow.newAuthorizationUrl()
                .setRedirectUri("http://localhost:8081/youtube")
                .build();
    }

    @Override
    public String[] getTokens(String code) {
        try {
            GoogleTokenResponse response = googleAuthorizationCodeFlow.newTokenRequest(code)
                    .setRedirectUri("http://localhost:8081/youtube")
                    .execute();

            return new String[]{response.getAccessToken(), ""};
        } catch (IOException exception) {
            return null;
        }
    }
}
