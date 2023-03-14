import {Component, OnInit} from '@angular/core';
import {EnvService} from "../../providers/env.service";
import {Plat} from "../../models/plat";
import {PlatService} from "../../providers/plat.service";
import {RestaurantService} from "../../providers/restaurant.service";
import {Horaire} from "../../models/horaire";
import {Router} from "@angular/router";

@Component({
  selector: 'qa-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent  implements OnInit{

  URL_API:string

  plats:Plat[] = [];
  horaires:Horaire[] = [];
  currentPlatIdx = 0;
  captionHidden=true;
  constructor(private env:EnvService, private platsSrv:PlatService, private restaurantService:RestaurantService, private router:Router) {
    this.URL_API = env.SERVER_URL;

  }

  showCaption(){
    this.captionHidden = false;
  }

  hideCaption(){
    this.captionHidden = true;
  }
  changeIndex(number: number) {
    this.currentPlatIdx = this.mod((this.currentPlatIdx + number),this.plats.length);
  }

  mod(n:number, m:number) {
    return ((n % m) + m) % m;
  }

  ngOnInit(): void {
    this.platsSrv.getAllPlatsAPI().subscribe(value => {
      this.plats = value;
    })
    this.restaurantService.getHorairesAPI().subscribe(value => {
      this.horaires = value;
    })
  }

  navigateToReservation() {
    this.router.navigate(['/reserver'])
  }
}
