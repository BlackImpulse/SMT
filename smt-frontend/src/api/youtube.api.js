import axios from "axios";

class YoutubeApi {
    getTracks(token) {
        return axios.get("https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&myRating=like&key="+token, {
            headers: {
                "Authorization": "Bearer " + token,
            }
        }).then(res => {
            return res.data.items.map(item => {
                return {
                    internalId: item.id,
                    imageSrc: item.snippet.thumbnails.standard.url,
                    authorName: item.snippet.channelTitle,
                    name: item.snippet.title,
                    checked: false
                }
            })
        }).catch(err => console.error(err))
    }
    getAlbums() {

    }
    getPlaylists(token) {
        return axios.get("https://youtube.googleapis.com/youtube/v3/playlists?part=snippet%2CcontentDetails&maxResults=25&mine=true&key=" + token, {
            headers: {
                "Authorization": "Bearer " + token,
            }
        }).then(res => {
            return res.data.items.map(item => {
                return {
                    internalId: item.id,
                    imageSrc: item.snippet.thumbnails.standard.url,
                    authorName: item.snippet.channelTitle,
                    name: item.snippet.title,
                    checked: false
                }
            })
        }).catch(err => console.error(err))
    }
}

export default new YoutubeApi();
