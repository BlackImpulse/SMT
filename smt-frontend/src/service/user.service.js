import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/api/user/';

class UserService {
    getUserByUsername(username) {
        return axios.get(API_URL + `get?username=${username}`, {
            headers: authHeader()
        });
    }
    getMigrationsByUsername(username) {
        return axios.get(API_URL + `migrations?username=${username}`, {
            headers: authHeader()
        })
    }
    getAppsByUsername(username) {
        return axios.get(API_URL + `services?username=${username}`, {
            headers: authHeader()
        })
    }
}

export default new UserService();
