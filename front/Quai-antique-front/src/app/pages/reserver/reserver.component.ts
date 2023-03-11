import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/user";
import {UserService} from "../../providers/user.service";
import {Horaire} from "../../models/horaire";
import {RestaurantService} from "../../providers/restaurant.service";
import {ReservationService} from "../../providers/reservation.service";
import {Reservation} from "../../models/reservation";
import {formatDate} from "@angular/common";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ModalReservationComponent} from "./modal-reservation/modal-reservation.component";
import {EnvService} from "../../providers/env.service";

@Component({
  selector: 'qa-reserver',
  templateUrl: './reserver.component.html',
  styleUrls: ['./reserver.component.scss']
})
export class ReserverComponent {
  formReservation: FormGroup;
  user:Partial<User> = {};

  reservation:Partial<Reservation> = {dateReservation:new Date()};
  formSubmitted = false;

  nomAllergies:{nomAllergie:string, checked:boolean|undefined}[] = []
  modifMessage: any;
  today= new Date();
  horaires:Horaire|undefined;
  midiAvalaible= false;
  soirAvalaible=false;
  nbPlacesDisponibles: number | undefined;
  private soir=false;

  constructor(private fb:FormBuilder,
              private userSrv:UserService,
              private restaurantService:RestaurantService,
              private reservationService:ReservationService,
              private modalService:NgbModal,
              private env:EnvService){
    this.formReservation = this.fb.group({
      convives : [this.user.nbConvives, [Validators.min(0), Validators.max(20), Validators.required]],
      date : ["", [Validators.required]],
      repas : ["", [Validators.required]],
    })
    this.restaurantService.getHorairesCurrentDayAPI(this.restaurantService.jours[this.today.getDay()-1]).subscribe(value => {
      this.horaires = value;
      this.isMidiAvalaible();
      this.isSoirAvalaible();
    })
    this.nomAllergies = this.userSrv.nomAllergies;
  }

  get date(){
    return this.formReservation.get("date");
  }

  get repas(){
    return this.formReservation.get("repas");
  }

  get convives(){
    return this.formReservation.get("convives");
  }

  openModalConfirmation() {
    this.formSubmitted = true;
    if(this.formReservation.valid && this.horaires != undefined){
      this.env.dataModalReservation = {
        horaires : this.horaires,
        soir : this.soir,
      }
      this.modalService.open(ModalReservationComponent);
    }

  }

  isMidiAvalaible() {
    if(this.horaires ==undefined || this.reservation.dateReservation == undefined){
      this.midiAvalaible = false;
    } else {
      this.midiAvalaible =  this.isTrancheHoraireAvalaible(this.horaires?.fermetureDejeuner, this.reservation.dateReservation);
    }
  }

  isTrancheHoraireAvalaible(horaires:string, lastHour:Date){
    let hourOfReservation = new Date();
    hourOfReservation.setHours(hourOfReservation.getHours()+1)
    let convertedJSHours = this.changeJavaTimeToJSHour(horaires);
    lastHour.setHours(convertedJSHours[0], convertedJSHours[1], convertedJSHours[2])
    if (lastHour <= hourOfReservation){
      return false;
    }
    return true;
  }


  changeJavaTimeToJSHour(time:string){
    return [time.substring(0, time.indexOf(":")), time.substring(time.indexOf(":")+1, time.lastIndexOf(":")), time.substring(time.lastIndexOf(":")+1)]
      .map(value => Number.parseInt(value));
  }

  isSoirAvalaible() {
    if(this.horaires == undefined || this.reservation.dateReservation == undefined){
      this.soirAvalaible = false;
    } else {
      this.soirAvalaible =  this.isTrancheHoraireAvalaible(this.horaires?.fermetureDiner, this.reservation.dateReservation);
    }
  }

  getHoraires($event: any) {
    this.reservation.dateReservation =  new Date($event);
    this.formReservation.get("repas")?.setValue("");
    this.nbPlacesDisponibles = undefined;
    this.isMidiAvalaible();
    this.isSoirAvalaible();
  }

  showPlacesDisponibles($event:boolean) {
    if(this.reservation.dateReservation != undefined && $event){
      this.soir = $event;
      let date = formatDate(this.reservation.dateReservation, "yyyy-MM-dd", 'en-US');
      this.reservationService.getPlacesDispoAPI($event, date).subscribe(value => {
        this.nbPlacesDisponibles = value.nbPlacesRestantes;
      })
    }
  }
}
