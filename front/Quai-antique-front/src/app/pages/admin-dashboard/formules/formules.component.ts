import { Component } from '@angular/core';
import {MenuService} from "../../../providers/menu.service";
import {PlatService} from "../../../providers/plat.service";
import {Formule} from "../../../models/formule";
import {Plat} from "../../../models/plat";

@Component({
  selector: 'qa-formules',
  templateUrl: './formules.component.html',
  styleUrls: ['./formules.component.scss']
})
export class FormulesComponent {
  formules: Formule[] = [];
  plats: Plat[] = [];
  menus:string[] = [];

  constructor(private menuService:MenuService, private platService:PlatService) {
    this.menuService.getAllFormulesAPI().subscribe(value => {
      this.formules = value;
    });
    this.platService.getAllPlatsAPI().subscribe(value => {
      this.plats = value;
    })
    this.menuService.getAllMenusNameAPI().subscribe(value => {
      this.menus = value;
    })
  }

}
