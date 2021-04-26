package com.gmyf.smt.factory.impl;

import com.gmyf.smt.factory.api.ServiceApi;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

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

    private final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri().build();

    @Override
    public String getAuthUrl() {
        return authorizationCodeUriRequest.execute().toString();
    }
}
