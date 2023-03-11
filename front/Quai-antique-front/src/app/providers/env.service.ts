import { Injectable } from '@angular/core';
import {Horaire} from "../models/horaire";

@Injectable({
  providedIn: 'root'
})
export class EnvService {

  SERVER_URL = "http://localhost:8080"
  dataModalReservation: { soir: boolean; allergies: string[]; horaires: Horaire; nbPersonnes: number } | undefined;
  constructor() { }
}
