import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Formule} from "../../../../models/formule";
import {Plat} from "../../../../models/plat";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'qa-form-formule',
  templateUrl: './form-formule.component.html',
  styleUrls: ['./form-formule.component.scss']
})
export class FormFormuleComponent implements OnChanges {

  @Input() formule : Partial<Formule> = {};
  @Input() plats : Plat[] = [];
  formChangementFormule: FormGroup;
  checkPlat: {checked:boolean, plat:Plat}[] = [];

  constructor(private fb:FormBuilder) {
    this.formChangementFormule = this.fb.group({
      nom: ["", [Validators.required]],
      description: ["", [Validators.required]],
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.formule.plats != undefined){
      for (let plat of this.plats){
        this.checkPlat.push({
          checked: (this.formule.plats.filter(value => value.nomPlat == plat.nomPlat).length > 0),
          plat:plat})
      }
    }
  }
}
