import { Component } from '@angular/core';
import {RestaurantService} from "../../providers/restaurant.service";

@Component({
  selector: 'qa-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  message="";

  private jours = ["LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI", "SAMEDI", "DIMANCHE"]
  constructor(private restaurantService:RestaurantService) {
    this.restaurantService.getHorairesCurrentDayAPI(this.jours[new Date().getDay()-1]).subscribe({
      next: value => {
        this.message = "Ouvert ce jour entre "
          + value.ouvertureDejeuner +
          " - " +
          value.fermetureDejeuner +
          " et " +
          value.ouvertureDiner +
          " - "+
          value.fermetureDiner;
      },
      error: ()=>{
        this.message = "Fermé ce jour";
      }
    })
  }
}
