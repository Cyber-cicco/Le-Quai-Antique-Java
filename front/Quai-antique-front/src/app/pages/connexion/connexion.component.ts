import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationRequest} from "../../models/authentication-request";
import {UserService} from "../../providers/user.service";
import {Router} from "@angular/router";


@Component({
  selector: 'qa-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss']
})
export class ConnexionComponent {
  formConnexion: FormGroup;
  authReq:Partial<AuthenticationRequest> = {};
  errConnexion = "";

  constructor(private fb:FormBuilder, private userServ:UserService, private router:Router){
    this.formConnexion = this.fb.group({
      email:["", [Validators.required, Validators.email]],
      password:["", [Validators.required, Validators.minLength(4)]]
    })
  }

  get nom(){
    return this.formConnexion.get("nom");
  }

  get prenom(){
    return this.formConnexion.get("prenom");
  }

  get email(){
    return this.formConnexion.get("email");
  }

  get password(){
    return this.formConnexion.get("password")
  }

  authenticate() {
    this.authReq = {
      email: this.formConnexion.get("email")?.value,
      password: this.formConnexion.get("password")?.value
    }
    this.userServ.postToConnexionAPI(this.authReq).subscribe({
      next:(value)=>{
        localStorage.setItem("token", value.token);
        this.router.navigate(['/profil/infos'])
      },
      error:()=>{
        this.errConnexion = "Erreur dans le nom d'utilisateur ou le mot de passe";
        this.formConnexion.get("password")?.setValue("");
      }
    })
  }
}
