import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Plat} from "../../../../models/plat";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EnvService} from "../../../../providers/env.service";
import {UserService} from "../../../../providers/user.service";
import {PlatNotTakenService} from "../../../../validators/plat-not-taken.service";
import {PlatService} from "../../../../providers/plat.service";

@Component({
  selector: 'qa-form-plat',
  templateUrl: './form-plat.component.html',
  styleUrls: ['./form-plat.component.scss']
})
export class FormPlatComponent implements OnChanges{

  @Input() plat:Partial<Plat> = {};

  nomAllergies: {nomAllergie:string, checked:boolean|undefined}[];

  formChangementPlat: FormGroup;

  URL:string

  nrSelect:string|undefined;
  feedbackMessage = "";
  fileName='';

  formData:FormData = new FormData();

  constructor(private fb:FormBuilder,
              private userService:UserService,
              private env:EnvService,
              private platValidator:PlatNotTakenService,
              private platService:PlatService){
    this.URL = env.SERVER_URL;
    this.formChangementPlat = fb.group({
      nom: ["", [Validators.minLength(2), Validators.maxLength(255)], [this.platValidator.validate.bind(this.platValidator)]],
      description: ["", [Validators.minLength(12), Validators.maxLength(2000)]],
      type:["",[]],
      prix:[this.plat.prix,[Validators.min(2), Validators.max(1000)]],
    })
    this.nomAllergies = structuredClone(this.userService.nomAllergies);
  }

  onFileSelected(event:any) {
    const file:File = event.target.files[0];
    if (file) {
      this.fileName = file.name;
      this.formData.append("file", file);
    }
  }

  get nom(){
    return this.formChangementPlat.get("nom");
  }

  get description(){
    return this.formChangementPlat.get("description");
  }

  get type(){
    return this.formChangementPlat.get("type");
  }

  get prix(){
    return this.formChangementPlat.get("prix");
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.nrSelect = this.plat.typePlat;
    console.log(this.nrSelect);
    console.log(this.plat.typePlat);
    this.nomAllergies.forEach(allergie=>{
      allergie.checked = this.plat.allergenes?.includes(allergie.nomAllergie);
    })
  }


  changePlat() {
    if(this.formChangementPlat.valid){
      let newNomPlat = this.nom?.value;
      let newDescription = this.description?.value;
      let newPrix = this.prix?.value;
      let newType = this.type?.value;
      let token = localStorage.getItem("token");

      if(newType != "") this.plat.typePlat = newType;
      if(newNomPlat != "") this.plat.nomPlat = newNomPlat;
      if(newDescription != "") this.plat.description = newDescription;
      if(newPrix != "") this.plat.prix = newPrix;
      this.plat.allergenes = this.nomAllergies.filter(value => value.checked).map(value => value.nomAllergie);

      this.platService.patchPlatAPI(this.plat, token).subscribe({
        next : value => {
          this.feedbackMessage = value.message;
          this.platService.postPhotoAPI(this.formData, this.plat.id, token).subscribe();
        },
        error : ()=>{
          this.feedbackMessage = "une erreur est survenue";
        }
      })
    } else {
      this.feedbackMessage = "formulaire invalide";
    }
  }
}
