import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/api/token/';

class TokenService {
    getToken(userId, serviceId) {
        return axios.get(API_URL + `get?userId=${userId}&serviceId=${serviceId}`, {
            headers: authHeader()
        });
    }

    saveToken(token, code) {
        return axios.post(API_URL + `save?code=${code}`, {
            ...token
        }, {
            headers: authHeader()
        })
    }

    deleteByUsernameAndServiceId(username, id) {
        return axios.delete(API_URL + `delete/${username}/${id}`, {
            headers: authHeader()
        });
    }
}

export default new TokenService();
