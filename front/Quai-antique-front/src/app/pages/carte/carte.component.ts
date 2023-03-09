import {Component} from '@angular/core';
import {Plat} from "../../models/plat";
import {EnvService} from "../../providers/env.service";
import {PlatService} from "../../providers/plat.service";
import {RestaurantService} from "../../providers/restaurant.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Menu} from "../../models/menu";
import {MenuService} from "../../providers/menu.service";
import {UserService} from "../../providers/user.service";
import {User} from "../../models/user";

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
  isConnected;

  user:Partial<User> = {};

  constructor(private env:EnvService,
              private platsSrv:PlatService,
              private restaurantService:RestaurantService,
              private fb:FormBuilder,
              private userService:UserService,
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
    this.isConnected = this.userService.isConnected;
    this.userService.getUserSubject().asObservable().subscribe(value =>{
      this.user = value
    });

  }

  changeCarouselPlats($event: any) {
    this.platsToDisplay = this.plats.filter(plat=> plat.typePlat.includes($event))
  }

  changeCarouselMenus($event: any) {
    if(this.menus.length > 0){
      this.menu = this.menus.filter(menu=>menu.nomMenu == $event)[0];
      this.irrigatePlatsOfMenu();
    }
  }

  private irrigatePlatsOfMenu() {
    this.platsOfMenu = new Set(this.menu.formules
      ?.map(formule=>formule.plats
        .map(plat => plat.photo))
      .flat());
  }

  checkAllergenes(plat:Plat) {
    return plat.allergenes.filter(allergene=>this.user.allergenes?.includes(allergene)).length > 0;
  }

  findAllergene(plat: Plat) {
    return [...plat.allergenes.filter(allergene=>this.user.allergenes?.includes(allergene))];
  }
}
