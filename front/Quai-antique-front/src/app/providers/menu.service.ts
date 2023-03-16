import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MenuGet} from "../models/menu-get";
import {EnvService} from "./env.service";
import {Formule} from "../models/formule";
import {MenuPost} from "../models/menu-post";

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private URL_API_MENUS:string;

  private URL_API_FORMULES:string;

  private URL_API_PATCH_MENU:string;

  private URL_API_CHECK_MENU:string

  constructor(private http:HttpClient, private env:EnvService) {
    this.URL_API_MENUS = this.env.SERVER_URL + "/menus/list";
    this.URL_API_FORMULES = this.env.SERVER_URL + "/menus/formules";
    this.URL_API_PATCH_MENU = this.env.SERVER_URL + "/admin/menu";
    this.URL_API_CHECK_MENU = this.env.SERVER_URL + "/admin/menu/check";
  }


  getAllMenuAPI() {
    return this.http.get<MenuGet[]>(this.URL_API_MENUS);
  }

  getAllFormulesAPI() {
    return this.http.get<Formule[]>(this.URL_API_FORMULES);
  }

  patchMenuAPI(menu: Partial<MenuPost>, token: string | null){
    return this.http.patch<{message:string, menu:MenuGet}>(this.URL_API_PATCH_MENU, menu, {headers:{
        "Authorization": "Bearer "+token,
        "Content-type": "application/json"}})
  }

  isNomMenuTaken(value: string, token:string|null) {
    return this.http.get<{exists:boolean}>(this.URL_API_CHECK_MENU+"?nom="+value, {headers:{
        "Authorization": "Bearer "+token,
        "Content-type": "application/json"}});
  }
}
