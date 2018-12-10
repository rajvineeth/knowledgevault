export class UserDetails {
    firstname: string;
    lastname: string;
    username: string;
    role: string;
    password: string;

    constructor(fn, ln, un, r, pwd) {
        this.firstname = fn;
        this.lastname = ln;
        this.username = un;
        this.role = r;
        this.password = pwd;
    }
}
