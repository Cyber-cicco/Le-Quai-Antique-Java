import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/user";
import {UserService} from "../../providers/user.service";
import {Horaire} from "../../models/horaire";
import {RestaurantService} from "../../providers/restaurant.service";
import {ReservationService} from "../../providers/reservation.service";
import {Reservation} from "../../models/reservation";

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
  horaires:Horaire|undefined = undefined;
  midiAvalaible= false;
  soirAvalaible=false;

  constructor(private fb:FormBuilder,
              private userSrv:UserService,
              private restaurantService:RestaurantService,
              private reservationService:ReservationService){
    this.formReservation = this.fb.group({
      nom : [this.user.nom, [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      prenom : [this.user.prenom, [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      email : [this.user.email, [Validators.required, Validators.email]],
      convives : [this.user.nbConvives, [Validators.min(0), Validators.max(20)]],
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

  get nom(){
    return this.formReservation.get("nom");
  }
  get prenom(){
    return this.formReservation.get("prenom");
  }

  get email(){
    return this.formReservation.get("email");
  }

  get convives(){
    return this.formReservation.get("convives");
  }

  openModalConfirmation() {

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

  getHoraires() {

  }
}
