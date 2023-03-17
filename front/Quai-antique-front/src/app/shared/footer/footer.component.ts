import { Component } from '@angular/core';
import {RestaurantService} from "../../providers/restaurant.service";

@Component({
  selector: 'qa-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  message="";

  constructor(private restaurantService:RestaurantService) {
    this.restaurantService.getHorairesCurrentDayAPI(this.restaurantService.jours[new Date().getDay()]).subscribe({
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
