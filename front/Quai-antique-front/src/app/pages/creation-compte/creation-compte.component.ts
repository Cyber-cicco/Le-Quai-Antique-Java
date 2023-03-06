import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../providers/user.service";
import {Router} from "@angular/router";
import {RegisterRequest} from "../../models/register-request";

@Component({
  selector: 'qa-creation-compte',
  templateUrl: './creation-compte.component.html',
  styleUrls: ['./creation-compte.component.scss']
})
export class CreationCompteComponent {

  formCreation: FormGroup;
  formSubmitted = false;
  errConnexion = "";
  private regRequest:Partial<RegisterRequest> = {}

  constructor(private fb:FormBuilder, private userServ:UserService, private router:Router){
    this.formCreation = this.fb.group({
      nom : ["", [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      prenom : ["", [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      email : ["", [Validators.required, Validators.email]],
      password : ["", [Validators.required, Validators.minLength(4)]]
    })
  }

  get nom(){
    return this.formCreation.get("nom");
  }

  get prenom(){
    return this.formCreation.get("prenom");
  }

  get email(){
    return this.formCreation.get("email");
  }

  get password(){
    return this.formCreation.get("password")
  }

  register() {
    this.formSubmitted = true;
    if(this.formCreation.valid){
      this.regRequest = {
        nom: this.formCreation.get("nom")?.value,
        prenom: this.formCreation.get("prenom")?.value,
        email: this.formCreation.get("email")?.value,
        password: this.formCreation.get("password")?.value
      }
      this.userServ.postToRegisterAPI(this.regRequest).subscribe({
        next:(value)=>{
          localStorage.setItem("token", value.token);
          this.router.navigate(['/profil/infos'])
        },
        error:()=>{
          this.errConnexion = "L'adresse mail est déjà utilisée";
        }
      })
    }
  }
}
