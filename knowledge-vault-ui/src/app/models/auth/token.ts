export class Token {
    username: string;
    token: string;
    role: string;

    constructor(username, token) {
        this.username = username;
        this.token = token;
    }
}
