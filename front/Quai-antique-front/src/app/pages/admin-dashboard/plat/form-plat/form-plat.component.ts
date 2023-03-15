import {Component, Input} from '@angular/core';
import {Plat} from "../../../../models/plat";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EnvService} from "../../../../providers/env.service";
import {UserService} from "../../../../providers/user.service";

@Component({
  selector: 'qa-form-plat',
  templateUrl: './form-plat.component.html',
  styleUrls: ['./form-plat.component.scss']
})
export class FormPlatComponent {

  @Input() plat:Partial<Plat> = {};
  formChangementPlat: FormGroup;

  URL:string
  nomAllergies: {nomAllergie:string, checked:boolean|undefined}[];

  constructor(private fb:FormBuilder, private userService:UserService, private env:EnvService) {
    this.URL = env.SERVER_URL;
    this.formChangementPlat = fb.group({
      nom:[this.plat.nomPlat,[Validators.required]],
      description:[this.plat.description,[Validators.required]],
      prix:[this.plat.prix,[Validators.required]],
      photo:[this.plat.photo,[Validators.required]]
    })
    this.nomAllergies = structuredClone(this.userService.nomAllergies);
  }

  get nom(){
    return this.formChangementPlat.get("nom");
  }

}
