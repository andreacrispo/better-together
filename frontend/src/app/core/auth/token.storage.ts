import { Injectable } from '@angular/core';


@Injectable({ providedIn: 'root'})
export class TokenStorage {

    public TOKEN_KEY = 'AuthToken';

    constructor() {}

    public saveToken(token: string) {
        window.sessionStorage.removeItem(this.TOKEN_KEY);
        window.sessionStorage.setItem(this.TOKEN_KEY, token);
    }

    public getToken(): string {
        return sessionStorage.getItem(this.TOKEN_KEY);
    }


    public logout() {
        window.sessionStorage.removeItem(this.TOKEN_KEY);
        window.sessionStorage.clear();
    }

}
