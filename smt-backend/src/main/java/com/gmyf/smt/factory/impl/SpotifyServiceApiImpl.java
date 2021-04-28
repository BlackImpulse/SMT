package com.gmyf.smt.factory.impl;

import com.gmyf.smt.factory.api.ServiceApi;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;

public class SpotifyServiceApiImpl implements ServiceApi {
    private final String clientId = "a098e53524d941919285a4269e13dfb5";
    private final String clientSecret = "a8580e8c874c4273b18462c18f1927bf";
    private final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8081/spotify");

    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    private final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
            .scope("user-library-read user-library-modify")
            .build();

    @Override
    public String getAuthUrl() {
        return authorizationCodeUriRequest.execute().toString();
    }

    @Override
    public String[] getTokens(String code) {
        try {
            AuthorizationCodeCredentials authorizationCodeCredentials = spotifyApi.authorizationCode(code).build().execute();
            return new String[] {authorizationCodeCredentials.getAccessToken(),
                    authorizationCodeCredentials.getRefreshToken(),
                    authorizationCodeCredentials.getExpiresIn().toString()};
        } catch (ParseException | SpotifyWebApiException | IOException exception) {
            return null;
        }
    }

    @Override
    public String[] getNewAccessToken(String refreshToken) {
        try {
            AuthorizationCodeCredentials authorizationCodeCredentials =
                    spotifyApi.authorizationCodeRefresh(clientId, clientSecret, refreshToken)
                            .build()
                            .execute();
            return new String[] { authorizationCodeCredentials.getAccessToken(),
                    authorizationCodeCredentials.getExpiresIn().toString() };
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            return null;
        }
    }
}
