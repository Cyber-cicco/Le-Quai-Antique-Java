import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Menu} from "../models/menu";
import {EnvService} from "./env.service";
import {Formule} from "../models/formule";

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private URL_API_MENUS:string;
  private URL_API_FORMULES:string;
  constructor(private http:HttpClient, private env:EnvService) {
    this.URL_API_MENUS = this.env.SERVER_URL + "/menus/list";
    this.URL_API_FORMULES = this.env.SERVER_URL + "/menus/formules";
  }


  getAllMenuAPI() {
    return this.http.get<Menu[]>(this.URL_API_MENUS);
  }

  getAllFormulesAPI() {
    return this.http.get<Formule[]>(this.URL_API_FORMULES);
  }
}
