import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Menu} from "../models/menu";
import {EnvService} from "./env.service";

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private URL_API_MENUS:string
  constructor(private http:HttpClient, private env:EnvService) {
    this.URL_API_MENUS = this.env.SERVER_URL + "/menus/list";
  }

  getAllMenuAPI() {
    return this.http.get<Menu[]>(this.URL_API_MENUS);
  }
}
