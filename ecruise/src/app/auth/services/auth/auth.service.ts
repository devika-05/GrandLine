import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

const BASE_URL=["http://localhost:8085"]

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  register(signupRequest:any):Observable<any>{
    return this.http.post<any>(BASE_URL+"/auth/signup", signupRequest);
  }

  login(loginRequest:any): Observable<any>{
    return this.http.post(BASE_URL+"/auth/login", loginRequest);
  }
}
