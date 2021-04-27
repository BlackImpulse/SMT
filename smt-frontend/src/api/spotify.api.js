import SpotifyWebApi from "spotify-web-api-js";

let spotifyApi = new SpotifyWebApi();

class SpotifyApi {
    getTracks(token) {
        spotifyApi.setAccessToken(token);
        return spotifyApi.getMySavedTracks().then((data) => {
            return data.items.map(item => {
                return {
                    internalId: item.track.id,
                    imageSrc: item.track.album.images[0].url,
                    authorName: item.track.artists[0].name,
                    name: item.track.name,
                    checked: false
                }
            });
        });
    }
    getAlbums(token) {
        spotifyApi.setAccessToken(token);
        return spotifyApi.getMySavedAlbums().then((data) => {
            return data.items.map(item => {
                return {
                    internalId: item.album.id,
                    imageSrc: item.album.images[0].url,
                    authorName: item.album.artists[0].name,
                    name: item.album.name,
                    checked: false
                }
            });
        });
    }
    getPlaylists(token) {
        spotifyApi.setAccessToken(token);
        return spotifyApi.getMe().then(data => {
            return spotifyApi.getUserPlaylists(data.id).then(data => {
                return data.items.map(item => {
                    return {
                        internalId: item.id,
                        imageSrc: item.images.length === 0 ? "" : item.images[0].url,
                        authorName: item.owner.display_name,
                        name: item.name,
                        checked: false
                    }
                });
            });
        });
    }
}

export default new SpotifyApi();
