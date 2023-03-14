import { Component } from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {EnvService} from "../../../providers/env.service";
import {DateUtilService} from "../../../providers/date-util.service";
import {Reservation} from "../../../models/reservation";
import {ReservationService} from "../../../providers/reservation.service";
import {DataReservation} from "../../../models/data-reservation";

@Component({
  selector: 'qa-modal-reservation',
  templateUrl: './modal-reservation.component.html',
  styleUrls: ['./modal-reservation.component.scss']
})
export class ModalReservationComponent {

  dataReservation: DataReservation | undefined
  horairesReservation: number[][] = [];

  private reservation:Reservation | undefined;
  constructor(private modalService:NgbModal,
              private env:EnvService,
              private dateUtil:DateUtilService,
              private reservationService:ReservationService) {
    this.dataReservation =  this.env.dataModalReservation.getValue();
    if(this.dataReservation != undefined){
      let debutReservation = this.dataReservation.soir ? this.dataReservation.horaires.ouvertureDiner : this.dataReservation.horaires.ouvertureDejeuner;
      let finReservation = this.dataReservation.soir ? this.dataReservation.horaires.fermetureDiner : this.dataReservation.horaires.fermetureDejeuner;
      this.createRangeOfHoraires(debutReservation, finReservation);
    }
  }
  hideModal() {
    this.modalService.dismissAll();
  }

  private createRangeOfHoraires(debutReservation: string, finReservation: string) {
    let arrayDebut = this.dateUtil.changeJavaTimeToJSHour(debutReservation);
    let arrayFin = this.dateUtil.changeJavaTimeToJSHour(finReservation);
    arrayFin[0]--;
    this.horairesReservation.push([arrayDebut[0], arrayDebut[1]]);
    while(arrayDebut[0] != arrayFin[0] && arrayDebut[1] != arrayFin[0]){
      if(arrayDebut[1] >= 45){
        arrayDebut[0]++;
        arrayDebut[1] = 0;
      } else {
        arrayDebut[1] += 15
      }
      this.horairesReservation.push([arrayDebut[0], arrayDebut[1]]);
    }
  }

  postReservation(horaire: number[]) {
    if(this.dataReservation != undefined){
      console.log(typeof this.dataReservation.date);
      this.dataReservation.date.setHours(horaire[0]+1, horaire[1]);
      this.reservation = {
        dateReservation:this.dataReservation.date,
        nbPlaces: this.dataReservation.nbPersonnes,
        restaurant: "Le Quai Antique Chamberry",
        soir: this.dataReservation.soir,
        allergenes : this.dataReservation.allergies,
        nom: this.dataReservation.nom,
        prenom: this.dataReservation.prenom

      }
      this.reservationService.postReservation(this.reservation, localStorage.getItem("token")).subscribe(value => {
          this.reservationService.nbPlacesRestanteSubject.next(value.nbPlacesRestantes);
          this.modalService.dismissAll();
          this.reservationService.reservationDoneSubject.next(true)
        }
      )
    }
  }
}
