export default class User {
    constructor(userId, token, serviceId) {
        this.user = {
            id: userId
        };
        this.token = token;
        this.service = {
            id: serviceId
        };
    }
}
