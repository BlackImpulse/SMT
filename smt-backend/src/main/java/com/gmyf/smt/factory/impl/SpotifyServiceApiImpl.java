package com.gmyf.smt.factory.impl;

import com.gmyf.smt.factory.api.ServiceApi;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.data.library.SaveTracksForUserRequest;
import com.wrapper.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            .scope("user-library-read user-library-modify playlist-modify-public")
            .build();

    @Override
    public String getAuthUrl() {
        return authorizationCodeUriRequest.execute().toString();
    }

    @Override
    public String[] getTokens(String code) {
        try {
            AuthorizationCodeCredentials authorizationCodeCredentials = spotifyApi.authorizationCode(code).build().execute();
            return new String[]{authorizationCodeCredentials.getAccessToken(),
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
            return new String[]{authorizationCodeCredentials.getAccessToken(),
                    authorizationCodeCredentials.getRefreshToken(),
                    authorizationCodeCredentials.getExpiresIn().toString()};
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            return null;
        }
    }

    @Override
    public String searchTrackByName(String name, String accessToken) {
        spotifyApi.setAccessToken(accessToken);
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(name).build();
        try {
            Paging<Track> trackPaging = searchTracksRequest.execute();

            return trackPaging.getItems()[0].getId();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            return null;
        }
    }

    @Override
    public void addToLibrary(String id, String accessToken, String type) {
        switch (type) {
            case "track":
                addTrack(id, accessToken);
                break;
        }
    }

    @Override
    public String getMyPlaylistByName(String name, String accessToken) {
        spotifyApi.setAccessToken(accessToken);
        GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest =
                spotifyApi.getListOfCurrentUsersPlaylists().limit(50).offset(0).build();
        try {
            Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfCurrentUsersPlaylistsRequest.execute();

            PlaylistSimplified[] playlistSimplified = playlistSimplifiedPaging.getItems();
            for (int i = 0; i < playlistSimplifiedPaging.getTotal(); i++) {
                for (int j = 0; j < playlistSimplifiedPaging.getItems().length; j++) {
                    if (playlistSimplified[j].getName().equals(name)) {
                        return playlistSimplified[j].getId();
                    }
                }
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {

        }
        return null;
    }

    @Override
    public com.gmyf.smt.factory.model.Playlist getPlaylist(String id, String accessToken) {
        spotifyApi.setAccessToken(accessToken);
        GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(id).build();
        try {
            Playlist playlist = getPlaylistRequest.execute();
            List<String> names = Arrays.stream(playlist.getTracks().getItems())
                    .map(track -> {
                        Track trackCasted = (Track) track.getTrack();
                        return trackCasted.getArtists()[0].getName() + " " + trackCasted.getName();
                    })
                    .collect(Collectors.toList());
            return new com.gmyf.smt.factory.model.Playlist(playlist.getName(), names);
        } catch (IOException | SpotifyWebApiException | ParseException e) {

        }
        return null;
    }

    @Override
    public String createPlaylist(String name, String accessToken) {
        spotifyApi.setAccessToken(accessToken);
        CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(getUserId(accessToken), name).build();
        try {
            Playlist response = createPlaylistRequest.execute();
            return response.getId();
        } catch (IOException | SpotifyWebApiException | ParseException ignored) {
        }
        return null;
    }

    @Override
    public void addToPlaylist(String id, String name, String accessToken) {
        spotifyApi.setAccessToken(accessToken);
        name = name.replaceAll("\\(.*?\\)$", "");
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(name).build();
        try {
            Paging<Track> trackPaging = searchTracksRequest.execute();
            AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi.addItemsToPlaylist(id,
                    new String[]{trackPaging.getItems()[0].getUri()}).build();
            addItemsToPlaylistRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {

        }
    }

    private void addTrack(String id, String accessToken) {
        spotifyApi.setAccessToken(accessToken);
        SaveTracksForUserRequest saveTracksForUserRequest = spotifyApi.saveTracksForUser(id).build();
        try {
            saveTracksForUserRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException ignored) {
        }
    }

    private String getUserId(String accessToken) {
        spotifyApi.setAccessToken(accessToken);
        GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();
        try {
            User response = getCurrentUsersProfileRequest.execute();
            return response.getId();
        } catch (IOException | SpotifyWebApiException | ParseException ignored) {
        }
        return null;
    }
}
