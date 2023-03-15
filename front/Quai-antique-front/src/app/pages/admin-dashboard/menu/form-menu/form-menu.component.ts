import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Menu} from "../../../../models/menu";
import {Formule} from "../../../../models/formule";
import {MenuService} from "../../../../providers/menu.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'qa-form-menu',
  templateUrl: './form-menu.component.html',
  styleUrls: ['./form-menu.component.scss']
})
export class FormMenuComponent implements OnChanges {

  @Input() menu: Partial<Menu> = {};

  @Input() formules: Formule[] = [];

  checkFormules: { checked: boolean, formule: Formule }[] = [];

  formChangementMenu: FormGroup;

  constructor(private menuService: MenuService, private fb: FormBuilder) {
    this.formChangementMenu = this.fb.group({
      nom: ["", [Validators.required]],
      description: ["", [Validators.required]],
    })
  }


  ngOnChanges(changes: SimpleChanges): void {
    if(this.menu.formules != undefined){
      for (let formule of this.formules){
        this.checkFormules.push({
          checked: (this.menu.formules.filter(value => value.nomFormule == formule.nomFormule).length > 0),
          formule:formule})
      }
    }

  }

}
