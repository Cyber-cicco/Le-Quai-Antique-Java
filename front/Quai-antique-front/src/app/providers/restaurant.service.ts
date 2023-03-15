import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Horaire} from "../models/horaire";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private URL_RESTAURANT_HORAIRES = "http://localhost:8080/restaurant/horaires?restaurant=Le%20Quai%20Antique%20Chamberry";

  private URL_RESTAURANT_HORAIRE_CE_JOUR = "http://localhost:8080/restaurant/horaires_jour?restaurant=Le%20Quai%20Antique%20Chamberry&day=";

  jours = [ "DIMANCHE","LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI", "SAMEDI"];

  constructor(private http:HttpClient) { }

  getHorairesAPI(){
    return this.http.get<Horaire[]>(this.URL_RESTAURANT_HORAIRES);
  }

  getHorairesCurrentDayAPI(jourSemaine:string){
    return this.http.get<Horaire>(this.URL_RESTAURANT_HORAIRE_CE_JOUR+jourSemaine)
  }
}
