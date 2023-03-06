import { Injectable } from '@angular/core';
import {AuthenticationRequest} from "../models/authentication-request";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthenticationResponse} from "../models/authentication-response";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private URL_USER_AUTH = "http://localhost:8080/connexion/authenticate"

  private URL_USER_REGISTER = "http://localhost:8080/connexion/register";

  private URL_CHECK_CONNEXION = "http://localhost:8080/connexion/isconnected";

  private isConnectedSubject:Subject<boolean> = new Subject<boolean>();
  constructor(private http:HttpClient) {}

  postToConnexionAPI(authReq: Partial<AuthenticationRequest>) {
    return this.http.post<AuthenticationResponse>(this.URL_USER_AUTH, authReq, {headers:{'Content-type': 'application/json'}});
  }

  postToRegisterAPI(regReq: Partial<AuthenticationRequest>){
    return this.http.post<AuthenticationResponse>(this.URL_USER_REGISTER, regReq);
  }

  getIsConnectedSubject(){
    return this.isConnectedSubject;
  }

  getIsConnectedSubjectAsObservable(){
    return this.isConnectedSubject.asObservable();
  }

  checkConnexion(token:string){
    return this.http.post<AuthenticationResponse>("http://localhost:8080/connexion/isconnected", {}, {headers:{
      "Authorization": "Bearer "+token,
      "Content-type": "text/plain"}});
  }
}
