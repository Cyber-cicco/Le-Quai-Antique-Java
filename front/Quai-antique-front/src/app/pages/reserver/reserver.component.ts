import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../../models/user";
import {UserService} from "../../providers/user.service";

@Component({
  selector: 'qa-reserver',
  templateUrl: './reserver.component.html',
  styleUrls: ['./reserver.component.scss']
})
export class ReserverComponent {
  formReservation: FormGroup;
  user:Partial<User> = {}
  formSubmitted = false;

  nomAllergies:{nomAllergie:string, checked:boolean|undefined}[] = []
  modifMessage: any;
  today= new Date();

  constructor(private fb:FormBuilder, private userSrv:UserService){
    this.formReservation = this.fb.group({
      nom : [this.user.nom, []],
      prenom : [this.user.prenom, []],
      email : [this.user.email, []],
      convives : [this.user.nbConvives, []]
    })
    this.nomAllergies = this.userSrv.nomAllergies;
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
}
