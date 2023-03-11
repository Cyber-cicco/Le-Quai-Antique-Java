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

  soir:boolean =false
  horaire:Horaire|undefined
  constructor(private modalService:NgbModal, private env:EnvService) {
    if(this.env.dataModalReservation != undefined){
      this.soir  = this.env.dataModalReservation.soir;
      this.horaire = this.env.dataModalReservation.horaires;
    }
  }
  hideModal() {
    this.modalService.dismissAll();
  }
}
