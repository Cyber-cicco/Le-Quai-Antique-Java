import {Horaire} from "./horaire";

export interface DataReservation {
  soir: boolean;
  allergies: string[];
  horaires: Horaire;
  nbPersonnes: number;
  date:Date,
  nom:string,
  prenom:string
}
