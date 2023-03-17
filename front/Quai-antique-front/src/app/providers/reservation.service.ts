import { Injectable } from '@angular/core';
import {EnvService} from "./env.service";
import {HttpClient} from "@angular/common/http";
import {Reservation} from "../models/reservation";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private URL_RESERVATION:string
  nbPlacesRestanteSubject: Subject<number> = new Subject<number>();
  reservationDoneSubject: Subject<boolean> = new Subject<boolean>();
  constructor(private env:EnvService, private http:HttpClient) {
    this.URL_RESERVATION = this.env.SERVER_URL + "/reservations"
  }

  getPlacesDispoAPI(soir:boolean, date:String){
    return this.http.get<{nbPlacesRestantes:number}>(this.URL_RESERVATION+ "?restaurant=Le%20Quai%20Antique%20Chamberry&" +"soir="+soir+"&date="+date)
  }

  postReservation(reservation: Reservation, token: string | null) {
    return this.http.post<{nbPlacesRestantes:number}>(this.URL_RESERVATION, reservation, (token != null) ? {headers:{
      "Authorization": "Bearer "+token,
        "Content-type": "application/json"}}:{});
  }
}
