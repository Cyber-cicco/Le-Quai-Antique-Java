import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Formule} from "../../../../models/formule";
import {Plat} from "../../../../models/plat";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MenuService} from "../../../../providers/menu.service";
import {FormuleNotTakenService} from "../../../../validators/formule-not-taken.service";

@Component({
  selector: 'qa-form-formule',
  templateUrl: './form-formule.component.html',
  styleUrls: ['./form-formule.component.scss']
})
export class FormFormuleComponent implements OnChanges {

  @Input() formule : Partial<Formule> = {};

  @Input() menus:string[] | undefined;

  @Input() plats : Plat[] = [];

  formChangementFormule: FormGroup;
  checkPlat: {checked:boolean, plat:Plat}[] = [];
  nrSelect: string = "";
  feedbackMessage = "";

  constructor(private fb:FormBuilder, private menuService:MenuService, private nomFormuleValidator:FormuleNotTakenService) {
    this.formChangementFormule = this.fb.group({
      nom: ["", [Validators.minLength(2), Validators.maxLength(255)], [this.nomFormuleValidator.validate.bind(this.nomFormuleValidator)]],
      description: ["", [Validators.minLength(12), Validators.maxLength(2000)]],
      menu:["",[]]
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.formule.plats != undefined){
      this.checkPlat = [];
      for (let plat of this.plats){
        this.checkPlat.push({
          checked: (this.formule.plats.filter(value => value.nomPlat == plat.nomPlat).length > 0),
          plat:plat})
      }
    }
    if(this.menus != undefined){
      this.nrSelect =  this.menus.filter(menu=> this.formule.menu == menu)[0];
    }
  }

  get nom(){
    return this.formChangementFormule.get("nom")
  }

  get description(){
    return this.formChangementFormule.get("description")
  }

  changeFormule() {
    if(this.formChangementFormule.valid){
      let nom = this.formChangementFormule.get("nom")?.value;
      let description = this.formChangementFormule.get("description")?.value;
      let menu = this.formChangementFormule.get("menu")?.value;
      if(nom != "") this.formule.nomFormule = nom;
      if(description != "") this.formule.description = description;
      this.formule.menu = menu;
      this.formule.plats = this.checkPlat.filter(plat => plat.checked).map(plat => plat.plat);
      this.menuService.patchFormuleAPI(this.formule, localStorage.getItem("token")).subscribe({
        next : value => {
          this.feedbackMessage = value.message
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
