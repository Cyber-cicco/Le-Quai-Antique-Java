import { Component } from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {EnvService} from "../../../providers/env.service";
import {Horaire} from "../../../models/horaire";

@Component({
  selector: 'qa-modal-reservation',
  templateUrl: './modal-reservation.component.html',
  styleUrls: ['./modal-reservation.component.scss']
})
export class ModalReservationComponent {

  dataReservation: { soir: boolean; allergies: string[]; horaires: Horaire; nbPersonnes: number } | undefined
  constructor(private modalService:NgbModal, private env:EnvService) {
    if(this.env.dataModalReservation != undefined){
      this.dataReservation  = this.env.dataModalReservation;
    }
  }
  hideModal() {
    this.modalService.dismissAll();
  }
}
