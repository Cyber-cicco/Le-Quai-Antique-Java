import { Component } from '@angular/core';
import {Restaurant} from "../../../models/restaurant";
import {RestaurantService} from "../../../providers/restaurant.service";
import {Horaire} from "../../../models/horaire";

@Component({
  selector: 'qa-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.scss']
})
export class RestaurantComponent {
  restaurant: Partial<Restaurant> = {};

  horaires:Horaire[] = []

  constructor(private restaurantService:RestaurantService) {
    this.restaurantService.getRestaurant().subscribe(value => {
      this.restaurant = value;
    })
    this.restaurantService.getHorairesAPI().subscribe(value => {
      this.horaires = value;
    })
  }

}
