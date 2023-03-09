import { Injectable } from '@angular/core';
import {AuthenticationRequest} from "../models/authentication-request";
import {HttpClient} from "@angular/common/http";
import {AuthenticationResponse} from "../models/authentication-response";
import {Subject} from "rxjs";
import {EnvService} from "./env.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private URL_USER_AUTH:string;

  private URL_USER_REGISTER:string;

  private URL_CHECK_CONNEXION:string;

  private isConnectedSubject:Subject<boolean> = new Subject<boolean>();
  isConnected = false;
  constructor(private http:HttpClient, private env:EnvService) {
    this.URL_CHECK_CONNEXION = env.SERVER_URL + "/connexion/isconnected";
    this.URL_USER_AUTH = env.SERVER_URL + "/connexion/authenticate";
    this.URL_USER_REGISTER = env.SERVER_URL + "/connexion/register";
  }

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
    return this.http.post<AuthenticationResponse>(this.URL_CHECK_CONNEXION, {}, {headers:{
      "Authorization": "Bearer "+token,
      "Content-type": "text/plain"}});
  }

  getUserInfosAPI(token: string | null) {
    return this.http.get
  }
}
