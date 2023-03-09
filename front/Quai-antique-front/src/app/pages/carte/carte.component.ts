import {Component} from '@angular/core';
import {Plat} from "../../models/plat";
import {EnvService} from "../../providers/env.service";
import {PlatService} from "../../providers/plat.service";
import {RestaurantService} from "../../providers/restaurant.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Menu} from "../../models/menu";
import {MenuService} from "../../providers/menu.service";

@Component({
  selector: 'qa-carte',
  templateUrl: './carte.component.html',
  styleUrls: ['./carte.component.scss']
})
export class CarteComponent{

  URL_API:string

  plats:Plat[] = [];
  formCategorie:FormGroup;
  platsToDisplay: Plat[]=[];
  menus:Menu[] = [];
  formMenus: FormGroup;
  menu:Partial<Menu>={}
  autoSelectMenu= "";
  platsOfMenu: Set<string> = new Set();

  constructor(private env:EnvService,
              private platsSrv:PlatService,
              private restaurantService:RestaurantService,
              private fb:FormBuilder,
              private menuService:MenuService) {
    this.URL_API = env.SERVER_URL;
    this.platsSrv.getAllPlatsAPI().subscribe(value => {
      this.plats = value;
      this.platsToDisplay = value;
    })


    this.menuService.getAllMenuAPI().subscribe(value => {
      this.menus =  value;
      this.autoSelectMenu = this.menus[0].nomMenu;
      this.menu = this.menus[0];
      this.irrigatePlatsOfMenu();

    })
    this.formCategorie = fb.group({
      categorie:[]
    })
    this.formMenus = fb.group({
      menus:[]
    })

  }

  changeCarouselPlats($event: any) {
    this.platsToDisplay = this.plats.filter(plat=> plat.typePlat.includes($event))
  }

  changeCarouselMenus($event: any) {
    this.menu = this.menus.filter(menu=>menu.nomMenu == $event)[0];
    this.irrigatePlatsOfMenu();
  }

  private irrigatePlatsOfMenu() {
    this.platsOfMenu = new Set(this.menu.formules
      ?.map(formule=>formule.plats
        .map(plat => plat.photo))
      .flat());
  }
}
