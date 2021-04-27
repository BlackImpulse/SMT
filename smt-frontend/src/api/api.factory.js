import SpotifyApi from './spotify.api';
import YoutubeApi from './youtube.api';

class ApiFactory {
    getApi(serviceId) {
        switch (serviceId) {
            case "1":
                return SpotifyApi;
            case "2":
                return YoutubeApi;
        }
    }
}

export default new ApiFactory();
