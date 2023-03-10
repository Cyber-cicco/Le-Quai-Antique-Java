import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
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
  modifMessage= "";

  constructor(private fb:FormBuilder, private userServ:UserService, private router:Router){
    if(!this.userServ.isConnected){
      let token = localStorage.getItem("token");
      if(token == null){
        router.navigate(["profil/connexion"]);
      } else {
        this.userServ.checkConnexion(token).subscribe({
          error:()=>{
            router.navigate(["profil/connexion"]);
          }
        })
      }
    }
    this.formCompte = this.fb.group({
      nom : [this.userServ.user.nom, [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      prenom : [this.userServ.user.prenom, [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      email : [this.userServ.user.email, [Validators.required, Validators.email]],
      convives: [this.userServ.user.nbConvives, [Validators.min(0), Validators.max(20)]]
    });
    this.userServ.getUserSubject().subscribe(value => this.user = value);
    this.user = this.userServ.getUserSubject().getValue();
    this.userServ.getUserSubject().asObservable().subscribe(value => {
      this.formCompte.get("nom")?.setValue(value.nom);
      this.formCompte.get("prenom")?.setValue(value.prenom);
      this.formCompte.get("email")?.setValue(value.email);
      this.formCompte.get("convives")?.setValue(value.nbConvives);
      this.nomAllergies.forEach(allergie=>{
        allergie.checked = this.user.allergenes?.includes(allergie.nomAllergie);
      })
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
    this.formSubmitted = true;
    if (this.formCompte.valid){
      this.user.allergenes= this.nomAllergies.filter(allergie=>allergie.checked).map(allergie=>allergie.nomAllergie);
      this.user.email=this.formCompte.get("email")?.value;
      this.user.nom=this.formCompte.get("nom")?.value;
      this.user.prenom=this.formCompte.get("prenom")?.value;
      this.user.nbConvives=this.formCompte.get("convives")?.value;
      this.userServ.updateUserAPI(this.user, localStorage.getItem("token")).subscribe(value => {
        this.modifMessage = "Modifications bien prises en compte";
        localStorage.setItem("token", value.token);
      })
    }

  }
}
