package com.gmyf.smt.factory.impl;

import com.gmyf.smt.factory.api.ServiceApi;
import com.gmyf.smt.factory.model.Playlist;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class YoutubeServiceApiImpl implements ServiceApi {
    private final String clientId = "1029476631579-00q1r5o50t6p45bknimoff51gk69f5pr.apps.googleusercontent.com";
    private final String clientSecret = "EcC6YmJkgu6z7hSIzK2BsIdy";
    private final List<String> scopes = Arrays.asList(YouTubeScopes.YOUTUBE, YouTubeScopes.YOUTUBE_FORCE_SSL);
    private final GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), GsonFactory.getDefaultInstance(), clientId, clientSecret, scopes)
            .setAccessType("offline")
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

            return new String[]{response.getAccessToken(), response.getRefreshToken(), response.getExpiresInSeconds().toString()};
        } catch (IOException exception) {
            return null;
        }
    }

    @Override
    public String[] getNewAccessToken(String refreshToken) {
        try {
            TokenResponse response = new GoogleRefreshTokenRequest(new NetHttpTransport(),
                    GsonFactory.getDefaultInstance(),
                    refreshToken,
                    clientId,
                    clientSecret).execute();

            return new String[]{response.getAccessToken(), response.getRefreshToken(), response.getExpiresInSeconds().toString()};
        } catch (IOException exception) {
            return null;
        }
    }

    @Override
    public String searchTrackByName(String name, String accessToken) {
        try {
            YouTube youTubeService = getService(accessToken);
            YouTube.Search.List request = youTubeService.search().list("snippet");
            SearchListResponse response = request.setMaxResults(25L).setQ(name).execute();
            if (response.getItems().size() != 0) {
                return response.getItems().get(0).getId().getVideoId();
            }
            return null;
        } catch (IOException exception) {
            return null;
        }
    }

    @Override
    public void addToLibrary(String id, String accessToken, String type) {
        try {
            YouTube youTubeService = getService(accessToken);
            YouTube.Videos.Rate request = youTubeService.videos().rate(id, "like");
            request.execute();
        } catch (IOException exception) {

        }
    }

    @Override
    public String getMyPlaylistByName(String name, String accessToken) {
        try {
            YouTube youTubeService = getService(accessToken);
            YouTube.Playlists.List request = youTubeService.playlists().list("snippet");
            PlaylistListResponse response = request.setMine(true).execute();
            for (com.google.api.services.youtube.model.Playlist playlist : response.getItems()){
                if (playlist.getSnippet().getTitle().equals(name)) {
                    return playlist.getId();
                }
            }
        } catch (IOException exception) {

        }
        return null;
    }

    @Override
    public Playlist getPlaylist(String id, String accessToken) {
        try {
            YouTube youTubeService = getService(accessToken);
            YouTube.Playlists.List requestList = youTubeService.playlists().list("snippet");
            PlaylistListResponse responseList = requestList.setId(id).execute();
            String playlistName = responseList.getItems().get(0).getSnippet().getTitle();
            YouTube.PlaylistItems.List requestsItems = youTubeService.playlistItems().list("snippet");
            PlaylistItemListResponse responseItems = requestsItems.setPlaylistId(id).execute();
            List<String> names = responseItems.getItems().stream()
                    .map(item -> item.getSnippet().getTitle())
                    .collect(Collectors.toList());
            return new Playlist(playlistName, names);
        } catch (IOException exception) {

        }
        return null;
    }

    @Override
    public String createPlaylist(String name, String accessToken) {
        try {
            YouTube youTubeService = getService(accessToken);
            com.google.api.services.youtube.model.Playlist playlist = new com.google.api.services.youtube.model.Playlist();
            PlaylistSnippet snippet = new PlaylistSnippet();
            snippet.setTitle(name);
            playlist.setSnippet(snippet);
            YouTube.Playlists.Insert request = youTubeService.playlists().insert("snippet, status", playlist);
            com.google.api.services.youtube.model.Playlist response = request.execute();
            return response.getId();
        } catch (IOException exception) {

        }
        return null;
    }

    @Override
    public void addToPlaylist(String id, String name, String accessToken) {
        String trackId = searchTrackByName(name, accessToken);
        try {
            YouTube youTubeService = getService(accessToken);
            PlaylistItem playlistItem = new PlaylistItem();
            PlaylistItemSnippet snippet = new PlaylistItemSnippet();
            snippet.setPlaylistId(id);
            ResourceId resourceId = new ResourceId();
            resourceId.setKind("youtube#video");
            resourceId.setVideoId(trackId);
            snippet.setResourceId(resourceId);
            playlistItem.setSnippet(snippet);
            YouTube.PlaylistItems.Insert request = youTubeService.playlistItems().insert("snippet", playlistItem);
            request.execute();
        } catch (IOException exception) {

        }
    }

    private YouTube getService(String accessToken) {
        TokenResponse response = new TokenResponse();
        response.setAccessToken(accessToken);
        Credential credential = new Credential(BearerToken.queryParameterAccessMethod()).setFromTokenResponse(response);
        return new YouTube.Builder(googleAuthorizationCodeFlow.getTransport(),
                googleAuthorizationCodeFlow.getJsonFactory(), credential)
                .build();
    }
}
