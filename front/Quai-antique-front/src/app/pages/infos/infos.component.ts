import { Component } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../providers/user.service";
import {Router} from "@angular/router";
import {User} from "../../models/user";

@Component({
  selector: 'qa-infos',
  templateUrl: './infos.component.html',
  styleUrls: ['./infos.component.scss']
})
export class InfosComponent {
  formCompte: FormGroup;

  formSubmitted=false;

  nomAllergies:{nomAllergie:string, checked:boolean|undefined}[] = [
    {nomAllergie : "Anhydrides sulfureux", checked:false},
    {nomAllergie : "Arachides", checked:false},
    {nomAllergie : "Céleri", checked:false},
    {nomAllergie : "Crustacés", checked:false},
    {nomAllergie : "Fruits à coque", checked:false},
    {nomAllergie : "Gluten", checked:false},
    {nomAllergie : "Graines de sésame", checked:false},
    {nomAllergie : "Lait", checked:false},
    {nomAllergie : "Lupin", checked:false},
    {nomAllergie : "Mollusques", checked:false},
    {nomAllergie : "Moutarde", checked:false},
    {nomAllergie : "Oeufs", checked:false},
    {nomAllergie : "Poissons", checked:false},
    {nomAllergie : "Soja", checked:false},
  ]

  user:Partial<User>={};

  constructor(private fb:FormBuilder, private userServ:UserService, private router:Router){
    if(!this.userServ.isConnected){
      router.navigate(["profil/connexion"]);
    }
    this.formCompte = this.fb.group({
      nom : ["", [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      prenom : ["", [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      email : ["", [Validators.required, Validators.email]],
      convives: ["", [Validators.min(0), Validators.max(20)]]
    });
    this.userServ.getUserSubject().subscribe(value => this.user = value);
    this.user = this.userServ.getUserSubject().getValue();
    this.nomAllergies.forEach(allergie=>{
      allergie.checked = this.user.allergenes?.includes(allergie.nomAllergie);
    })
  }

  get nom(){
    return this.formCompte.get("nom");
  }

  get prenom(){
    return this.formCompte.get("prenom");
  }

  get email(){
    return this.formCompte.get("email");
  }

  get convives(){
    return this.formCompte.get("convives");
  }

  change() {

  }

  changeAllergies($event: Event) {
  }
}
