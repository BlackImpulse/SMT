import axios from "axios";

class YoutubeApi {
    getTracks(token) {
        return axios.get("https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&myRating=like&key="+token, {
            headers: {
                "Authorization": "Bearer " + token,
            }
        }).then(res => {
            let regexp = new RegExp(" - Topic$");

            return res.data.items.map(item => {
                return {
                    imageSrc: item.snippet.thumbnails.standard.url,
                    authorName: regexp.test(item.snippet.channelTitle) ?
                        item.snippet.channelTitle.slice(0, item.snippet.channelTitle.length - 8) :
                        item.snippet.channelTitle,
                    name: item.snippet.title,
                    checked: false
                }
            })
        })
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
                    imageSrc: item.snippet.thumbnails.standard.url,
                    authorName: item.snippet.channelTitle,
                    name: item.snippet.title,
                    checked: false
                }
            })
        })
    }
}

export default new YoutubeApi();
