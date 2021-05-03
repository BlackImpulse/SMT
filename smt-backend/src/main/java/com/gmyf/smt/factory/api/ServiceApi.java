package com.gmyf.smt.factory.api;

import com.gmyf.smt.factory.model.Playlist;

import java.util.List;

public interface ServiceApi {
    String getAuthUrl();
    String[] getTokens(String code);
    String[] getNewAccessToken(String refreshToken);
    String searchTrackByName(String name, String accessToken);
    void addToLibrary(String id, String accessToken, String type);
    String getMyPlaylistByName(String name, String accessToken);
    Playlist getPlaylist(String id, String accessToken);
    String createPlaylist(String name, String accessToken);
    void addToPlaylist(String id, String name, String accessToken);
    default void migrateTracks(List<String> names, String accessToken) {
        names.forEach(name ->  {
            String id = searchTrackByName(name, accessToken);
            if (id == null) {
                return;
            }
            addToLibrary(id, accessToken, "track");
        });
    }
    default void migratePlaylists(List<Playlist> playlists, String accessToken) {
        playlists.forEach(playlist -> {
            String id = createPlaylist(playlist.getName(), accessToken);
            if (id == null) {
                return;
            }
            playlist.getTracksNames().forEach(name -> addToPlaylist(id, name, accessToken));
        });
    }
}
