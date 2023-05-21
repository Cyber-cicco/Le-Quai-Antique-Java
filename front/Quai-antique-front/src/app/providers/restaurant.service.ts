import { Injectable } from '@angular/core';
import {HttpClient, HttpStatusCode} from "@angular/common/http";
import {Horaire} from "../models/horaire";
import {EnvService} from "./env.service";
import {Restaurant} from "../models/restaurant";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private URL_RESTAURANT_HORAIRES:string;

  private URL_RESTAURANT_HORAIRE_CE_JOUR:string;

  private URL_RESTAURANT_INFOS:string;

  private URL_PATCH_RESTAURANT:string;

  jours = [ "DIMANCHE","LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI", "SAMEDI"];
  private URL_PATCH_HORAIRE: string;

  constructor(private http:HttpClient, private env:EnvService) {
    this.URL_RESTAURANT_HORAIRES = this.env.SERVER_URL + "/restaurant/horaires?restaurant=Le%20Quai%20Antique%20Chamberry";
    this.URL_RESTAURANT_HORAIRE_CE_JOUR = this.env.SERVER_URL + "/restaurant/horaires_jour?restaurant=Le%20Quai%20Antique%20Chamberry&day=";
    this.URL_RESTAURANT_INFOS = env.SERVER_URL + "/restaurant/infos?restaurant=Le%20Quai%20Antique%20Chamberry"
    this.URL_PATCH_RESTAURANT = env.SERVER_URL + "/admin/restaurant"
    this.URL_PATCH_HORAIRE = env.SERVER_URL + "/admin/horaire"
  }

  getHorairesAPI(){
    return this.http.get<Horaire[]>(this.URL_RESTAURANT_HORAIRES);
  }

  getHorairesCurrentDayAPI(jourSemaine:string){
    return this.http.get<Horaire>(this.URL_RESTAURANT_HORAIRE_CE_JOUR+jourSemaine)
  }

  getRestaurant() {
    return this.http.get<Restaurant>(this.URL_RESTAURANT_INFOS)
  }

  patchRestaurantAPI(restaurant: Partial<Restaurant>, token: string | null) {
    return this.http.patch<{message:string, restaurant:Restaurant}>(this.URL_PATCH_RESTAURANT, restaurant,{headers:{
        "Authorization": "Bearer "+token,
        "Content-type": "application/json"}});
  }

  patchHoraireAPI(horaire: Partial<Horaire>, token: string | null) {
    return this.http.patch<{message:string, horaire:Horaire}>(this.URL_PATCH_HORAIRE, horaire,{headers:{
        "Authorization": "Bearer "+token,
        "Content-type": "application/json"}});
  }
}
