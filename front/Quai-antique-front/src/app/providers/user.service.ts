import { Injectable } from '@angular/core';
import {AuthenticationRequest} from "../models/authentication-request";
import {HttpClient} from "@angular/common/http";
import {AuthenticationResponse} from "../models/authentication-response";
import {BehaviorSubject, Subject} from "rxjs";
import {EnvService} from "./env.service";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private URL_USER_AUTH:string;

  private URL_USER_REGISTER:string;

  private URL_CHECK_CONNEXION:string;

  private URL_GET_INFOS:string

  private URL_GET_ADMIN:string

  user:Partial<User> = {};

  private isConnectedSubject:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  private userSubject:BehaviorSubject<Partial<User>> = new BehaviorSubject<Partial<User>>({})

  isConnected = false;


  nomAllergies:{nomAllergie:string, checked:boolean|undefined}[] = [
    {nomAllergie : "Anhydrides sulfureux", checked:false},
    {nomAllergie : "Arachides", checked:false},
    {nomAllergie : "Céleri", checked:false},
    {nomAllergie : "Crustacés", checked:false},
    {nomAllergie : "Fruits à coque", checked:false},
    {nomAllergie : "Gluten", checked:false},
    {nomAllergie : "Graines de sésame", checked:false},
    {nomAllergie : "Lait", checked:false},
    {nomAllergie : "Lupin", checked:false},
    {nomAllergie : "Mollusques", checked:false},
    {nomAllergie : "Moutarde", checked:false},
    {nomAllergie : "Oeufs", checked:false},
    {nomAllergie : "Poissons", checked:false},
    {nomAllergie : "Soja", checked:false},
  ]

  constructor(private http:HttpClient, private env:EnvService) {
    this.URL_CHECK_CONNEXION = env.SERVER_URL + "/connexion/isconnected";
    this.URL_USER_AUTH = env.SERVER_URL + "/connexion/authenticate";
    this.URL_USER_REGISTER = env.SERVER_URL + "/connexion/register";
    this.URL_GET_INFOS = env.SERVER_URL + "/user/profil";
    this.URL_GET_ADMIN = env.SERVER_URL + "/admin/dashboard"
    this.userSubject.subscribe(value => this.user = value);
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

  getUserSubject(){
    return this.userSubject;
  }
  checkConnexion(token:string){
    return this.http.post<AuthenticationResponse>(this.URL_CHECK_CONNEXION, {}, {headers:{
      "Authorization": "Bearer "+token,
      "Content-type": "text/plain"}});
  }

  getUserInfosAPI(token: string | null) {
    return this.http.get<User>(this.URL_GET_INFOS,{headers:{
        "Authorization": "Bearer "+token,
        "Content-type": "text/plain"}});
  }

  updateUserAPI(user: Partial<User>, token: string | null) {
    return this.http.patch<AuthenticationResponse>(this.URL_GET_INFOS, user,{headers:{
        "Authorization": "Bearer "+token,
        "Content-type": "application/json"}})
  }

  checkIfAdmin(token: string | null) {
    return this.http.get(this.URL_GET_ADMIN, {headers:{
        "Authorization": "Bearer "+token,
        "Content-type": "application/json"}});
  }
}
