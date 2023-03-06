import { Injectable } from '@angular/core';
import {EnvService} from "./env.service";
import {HttpClient} from "@angular/common/http";
import {Plat} from "../models/plat";

@Injectable({
  providedIn: 'root'
})
export class PlatService {

  private URL_LIST_PLATS:string;
  constructor(private env:EnvService, private http:HttpClient) {
    this.URL_LIST_PLATS = env.SERVER_URL + "/plats/list"
  }


  getAllPlatsAPI(){
    return this.http.get<Plat[]>(this.URL_LIST_PLATS);
  }
}
