import { Component } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
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
import {DateUtilService} from "../../providers/date-util.service";
import {MaxConvivesService} from "../../validators/max-convives.service";

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
  modifMessage: string | undefined;
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
              private env:EnvService,
              private dateUtil:DateUtilService,
              private convivesValidator:MaxConvivesService){
    this.user = this.userSrv.user;
    this.formReservation = this.fb.group({
      convives : [this.user.nbConvives, [Validators.min(0), Validators.required, this.checkIfSufficientPlaces()], [convivesValidator.validate.bind(this.convivesValidator)]],
      nom : [this.userSrv.user.nom, [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      prenom : [this.userSrv.user.prenom, [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      date : ["", [Validators.required]],
      repas : ["", [Validators.required]],
    })
    this.restaurantService.getHorairesCurrentDayAPI(this.restaurantService.jours[this.today.getDay()]).subscribe(value => {
      this.horaires = value;
      this.isMidiAvalaible();
      this.isSoirAvalaible();
    })
    this.reservationService.nbPlacesRestanteSubject.asObservable().subscribe(value =>{
      this.nbPlacesDisponibles = value;
    })
    this.reservationService.reservationDoneSubject.asObservable().subscribe(() => {
      this.modifMessage = "Réservation bien prise en compte";
    })
    this.nomAllergies = this.userSrv.nomAllergies;
  }

  get date(){
    return this.formReservation.get("date");
  }

  get nom(){
    return this.formReservation.get("nom");
  }

  get prenom(){
    return this.formReservation.get("prenom");
  }

  get repas(){
    return this.formReservation.get("repas");
  }

  get convives(){
    return this.formReservation.get("convives");
  }

  openModalConfirmation() {
    this.formSubmitted = true;
    if(this.formReservation.valid && this.horaires != undefined && this.reservation.dateReservation != undefined){
      let allergies  = this.nomAllergies.filter(value => value.checked).map(value => value.nomAllergie);
      this.env.dataModalReservation.next({
        horaires : this.horaires,
        soir : this.soir,
        nbPersonnes: Number.parseInt(this.formReservation.get("convives")?.value),
        allergies : allergies,
        date: this.reservation.dateReservation,
        nom:this.formReservation.get("nom")?.value,
        prenom:this.formReservation.get("prenom")?.value
      })
      this.modalService.open(ModalReservationComponent);
    }

  }

  isMidiAvalaible() {
    if(this.horaires ==undefined || this.reservation.dateReservation == undefined){
      this.midiAvalaible = false;
    } else {
      this.midiAvalaible =  this.isTrancheHoraireAvalaible(this.horaires?.fermetureDejeuner, this.reservation.dateReservation) && this.horaires.ouvertDejeuner;
    }
  }

  isTrancheHoraireAvalaible(horaires:string, lastHour:Date){
    let hourOfReservation = new Date();
    hourOfReservation.setHours(hourOfReservation.getHours()+1)
    let convertedJSHours = this.dateUtil.changeJavaTimeToJSHour(horaires);
    lastHour.setHours(convertedJSHours[0], convertedJSHours[1], convertedJSHours[2])
    return lastHour > hourOfReservation;

  }

  isSoirAvalaible() {
    if(this.horaires == undefined || this.reservation.dateReservation == undefined){
      this.soirAvalaible = false;
    } else {
      this.soirAvalaible =  this.isTrancheHoraireAvalaible(this.horaires?.fermetureDiner, this.reservation.dateReservation) && this.horaires.ouvertDiner;
    }
  }

  getHoraires($event: any) {
    this.reservation.dateReservation =  new Date($event);
    this.formReservation.get("repas")?.setValue("");
    this.nbPlacesDisponibles = undefined;
    this.restaurantService.getHorairesCurrentDayAPI(this.restaurantService.jours[this.reservation.dateReservation.getDay()]).subscribe(value => {
      this.horaires = value;
      this.isMidiAvalaible();
      this.isSoirAvalaible();
      })
  }

  showPlacesDisponibles($event:string) {
    if(this.reservation.dateReservation != undefined && $event){
      this.soir = $event == "true";
      let date = formatDate(this.reservation.dateReservation, "yyyy-MM-dd", 'en-US');
      this.reservationService.getPlacesDispoAPI(this.soir, date).subscribe(value => {
        this.reservationService.nbPlacesRestanteSubject.next(value.nbPlacesRestantes);
      })
    }
  }

  private checkIfSufficientPlaces():ValidatorFn {
    return (control:AbstractControl) : ValidationErrors | null => {
      if(this.nbPlacesDisponibles == undefined) return null;
      return control.value >= this.nbPlacesDisponibles
        ? {error:"Le montant de convives invités dépasse le montant de personnes autorisées"}
        :null;
    }
  }
}
