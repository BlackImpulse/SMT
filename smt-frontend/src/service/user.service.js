import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/api/user/';

class UserService {
    getUserIdByUsername(username) {
        return axios.get(API_URL + `get?username=${username}`, {
            headers: authHeader()
        });
    }
}

export default new UserService();
