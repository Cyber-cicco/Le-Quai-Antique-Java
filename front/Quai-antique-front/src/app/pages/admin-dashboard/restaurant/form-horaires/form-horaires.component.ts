import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Horaire} from "../../../../models/horaire";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RestaurantService} from "../../../../providers/restaurant.service";

@Component({
  selector: 'qa-form-horaires',
  templateUrl: './form-horaires.component.html',
  styleUrls: ['./form-horaires.component.scss']
})
export class FormHorairesComponent implements OnChanges{

  @Input() horaires:Horaire[] = [];

  nrSelect = "";
  formHoraires: FormGroup;
  horaire: Partial<Horaire> = {};
  randate= new Date();
  feedbackMessage = "";


  constructor(private fb:FormBuilder, private restaurantService:RestaurantService) {
    this.formHoraires = this.fb.group({
      jour:['',[]],
      dejeunerDebut: [this.horaire.ouvertureDejeuner,[Validators.required]],
      dejeunerFin: [this.horaire.ouvertureDejeuner,[]],
      dinerDebut: [this.horaire.ouvertureDejeuner,[]],
      dinerFin: [this.horaire.ouvertureDejeuner,[]],
      ouvertDejeuner:["",[]],
      ouvertDiner:["",[]]
    })
  }

  get dejeunerDebut(){
    return this.formHoraires.get("dejeunerDebut");
  }

  get dejeunerFin(){
    return this.formHoraires.get("dejeunerFin");
  }

  get dinerDebut(){
    return this.formHoraires.get("dinerDebut");
  }

  get dinerFin(){
    return this.formHoraires.get("dinerFin");
  }

  get ouvertDejeuner(){
    return this.formHoraires.get("ouvertDejeuner")
  }

  get ouvertDiner(){
    return this.formHoraires.get("ouvertDiner")
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.changeHoraireFromWeekDay("LUNDI");
  }

  changeHoraireFromWeekDay(day:string){
    this.nrSelect = day;
    this.horaire = this.horaires.filter(value => value.jourSemaine == day)[0]
    this.dejeunerDebut?.setValue(this.horaire?.ouvertureDejeuner);
    this.dejeunerFin?.setValue(this.horaire?.fermetureDejeuner);
    this.ouvertDejeuner?.setValue(this.horaire?.ouvertDejeuner);
    this.dinerDebut?.setValue(this.horaire?.ouvertureDiner);
    this.ouvertDiner?.setValue(this.horaire?.ouvertDiner);
    this.dinerFin?.setValue(this.horaire?.fermetureDiner);

  }

  patchHoraire() {
    if(this.formHoraires.valid){
      this.horaire.ouvertDejeuner = this.ouvertDejeuner?.value;
      this.horaire.ouvertureDejeuner = this.dejeunerDebut?.value;
      this.horaire.fermetureDejeuner = this.dejeunerFin?.value;
      this.horaire.ouvertDiner = this.ouvertDiner?.value;
      this.horaire.ouvertureDiner = this.dinerDebut?.value;
      this.horaire.fermetureDiner = this.dinerFin?.value;
      this.restaurantService.patchHoraireAPI(this.horaire, localStorage.getItem("token")).subscribe({
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
