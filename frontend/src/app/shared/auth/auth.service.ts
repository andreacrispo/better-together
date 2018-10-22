import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorage } from './toke.storage';

@Injectable({ providedIn: 'root'})
export class AuthService {

  public BASE_URL =  'http://localhost:8080';
  public AUTH_API = this.BASE_URL + '/auth/login';
  public SIGNUP_API = this.BASE_URL + '/auth/signup';

  constructor(private http: HttpClient, private tokenStorage: TokenStorage) {}


  obtainToken(username: string, password: string) {
    const credentials = {
      username: username,
      password: password
    };
    const opts = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.post(this.AUTH_API, credentials, opts);
  }

  signup(username: string, password: string) {
      const credentials = {
        username: username,
        password: password
      };
      const opts = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json'
        })
      };
      return this.http.post(this.SIGNUP_API, credentials, opts);
  }

  saveToken(token: string) {
    this.tokenStorage.saveToken(token);
  }

  isAuthenticated(): boolean {
    if ( this.tokenStorage.getToken() != null) {
      return true;
    }
    return false;
  }

  logout() {
    this.tokenStorage.logout();
  }



}
