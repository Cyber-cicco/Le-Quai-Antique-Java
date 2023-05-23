import { Injectable } from '@angular/core';
import {Horaire} from "../models/horaire";
import {BehaviorSubject, Subject} from "rxjs";
import {DataReservation} from "../models/data-reservation";

@Injectable({
  providedIn: 'root'
})
export class EnvService {

  SERVER_URL = "http://35.180.231.223:8080"
  dataModalReservation = new BehaviorSubject<DataReservation | undefined>(undefined);
  constructor() { }
}
