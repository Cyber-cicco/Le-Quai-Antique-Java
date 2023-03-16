import { Component } from '@angular/core';
import {MenuGet} from "../../../models/menu-get";
import {Formule} from "../../../models/formule";
import {MenuService} from "../../../providers/menu.service";

@Component({
  selector: 'qa-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {

  menus:MenuGet[] = [];

  formules:Formule[] = [];

  constructor(private menuService:MenuService) {
    this.menuService.getAllMenuAPI().subscribe(value => {
      this.menus = value;
    });
    this.menuService.getAllFormulesAPI().subscribe(value => {
      this.formules = value;
    });
  }
}
