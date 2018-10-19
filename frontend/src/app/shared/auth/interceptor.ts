import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { TokenStorage } from "./toke.storage";

@Injectable({ providedIn: 'root'})
export class Interceptor implements HttpInterceptor {

    private TOKEN_HEADER_KEY = 'Authorization';
    private TOKEN_TYPE       = 'Bearer';

    constructor(private tokenStorage: TokenStorage) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authRequest = request;
        let token = this.tokenStorage.getToken();
        if(token != null){
           authRequest =  request.clone({
               headers: request.headers.set(this.TOKEN_HEADER_KEY, `${this.TOKEN_TYPE} ${token}`)
           })
        }
        console.log("auth  " + authRequest.headers.get(this.TOKEN_HEADER_KEY) );
        return next.handle(authRequest);
    }

}