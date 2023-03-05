import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'qa-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss']
})
export class ConnexionComponent {
  formConnexion: FormGroup;

  constructor(private fb:FormBuilder){
    this.formConnexion = this.fb.group({
      nom:["", [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      prenom:["",[Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      email:["", [Validators.required, Validators.email]],
      password:["", [Validators.required, Validators.minLength(8)]]
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

}
