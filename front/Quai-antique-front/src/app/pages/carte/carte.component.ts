import {Component} from '@angular/core';
import {Plat} from "../../models/plat";
import {EnvService} from "../../providers/env.service";
import {PlatService} from "../../providers/plat.service";
import {RestaurantService} from "../../providers/restaurant.service";
import {FormBuilder, FormGroup} from "@angular/forms";

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
  constructor(private env:EnvService, private platsSrv:PlatService, private restaurantService:RestaurantService, private fb:FormBuilder) {
    this.URL_API = env.SERVER_URL;
    this.platsSrv.getAllPlatsAPI().subscribe(value => {
      this.plats = value;
      this.platsToDisplay = value;
    })
    this.formCategorie = fb.group({
      categorie:[]
    })

  }

  changeCarousel($event: any) {
    this.platsToDisplay = this.plats.filter(plat=> plat.typePlat.includes($event))
  }
}
