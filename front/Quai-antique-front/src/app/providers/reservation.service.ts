import { Injectable } from '@angular/core';
import {EnvService} from "./env.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private URL_RESERVATION:string
  constructor(private env:EnvService, private http:HttpClient) {
    this.URL_RESERVATION = this.env.SERVER_URL + "/reservations?restaurant=Le%20Quai%20Antique%20Chamberry&"
  }

  getPlacesDispoAPI(soir:boolean, date:String){
    return this.http.get<{nbPlacesRestantes:number}>(this.URL_RESERVATION+"soir="+soir+"&date="+date)
  }
}
