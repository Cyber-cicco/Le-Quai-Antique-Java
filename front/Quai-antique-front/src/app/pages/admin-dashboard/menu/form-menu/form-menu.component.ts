import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {MenuGet} from "../../../../models/menu-get";
import {Formule} from "../../../../models/formule";
import {MenuService} from "../../../../providers/menu.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MenuPost} from "../../../../models/menu-post";
import {MenuNotTakenValidatorService} from "../../../../validators/menu-not-taken-validator.service";

@Component({
  selector: 'qa-form-menu',
  templateUrl: './form-menu.component.html',
  styleUrls: ['./form-menu.component.scss']
})
export class FormMenuComponent implements OnChanges {

  @Input() menu: Partial<MenuGet> = {};

  private menuPost:Partial<MenuPost> = {}

  @Input() formules: Formule[] = [];

  checkFormules: { checked: boolean, formule: Formule }[] = [];

  formChangementMenu: FormGroup;
  feedbackMessage = "";

  constructor(private menuService: MenuService, private fb: FormBuilder, private nomMenuValidator:MenuNotTakenValidatorService) {
    this.formChangementMenu = this.fb.group({
      nom: ["", [Validators.minLength(2), Validators.maxLength(255)], [this.nomMenuValidator.validate.bind(this.nomMenuValidator)]],
      description: ["", [Validators.minLength(12), Validators.maxLength(2000)]],
    })
  }

  get nom(){
    return this.formChangementMenu.get("nom")
  }

  get description(){
    return this.formChangementMenu.get("description")
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

  patchMenu() {
    if(this.formChangementMenu.valid){
      let newNomMenu = this.formChangementMenu.get("nom")?.value;
      let newDescription = this.formChangementMenu.get("description")?.value;
      this.menuPost.id = this.menu.id;
      this.menuPost.nomMenu = newNomMenu == ""? this.menu.nomMenu : newNomMenu;
      this.menuPost.description = newDescription == "" ? this.menu.description : newDescription;
      this.menuPost.formules = this.checkFormules.filter(value=> value.checked).map(value => value.formule.nomFormule);
      this.menuService.patchMenuAPI(this.menuPost, localStorage.getItem("token")).subscribe({
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
