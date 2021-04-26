import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/api/token/';

class TokenService {
    getToken(userId, serviceId) {
        return axios.get(API_URL + `get?userId=${userId}&serviceId=${serviceId}`, {
            headers: authHeader()
        });
    }

    saveToken(token) {
        return axios.post(API_URL + 'save', {
            ...token
        }, {
            headers: authHeader()
        })
    }
}

export default new TokenService();
