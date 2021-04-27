import ApiFactory from '../api/api.factory';

class ServiceService {
    getTracks(token, serviceId) {
        return ApiFactory.getApi(serviceId).getTracks(token);
    }
    getAlbums(token, serviceId) {
        return ApiFactory.getApi(serviceId).getAlbums(token);
    }
    getPlaylists(token, serviceId) {
        return ApiFactory.getApi(serviceId).getPlaylists(token);
    }
}

export default new ServiceService();
