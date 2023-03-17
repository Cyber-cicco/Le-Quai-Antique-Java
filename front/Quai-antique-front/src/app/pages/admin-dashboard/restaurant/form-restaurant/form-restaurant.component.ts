import {Component, Input} from '@angular/core';
import {Restaurant} from "../../../../models/restaurant";
import {FormBuilder, FormGroup} from "@angular/forms";
import {RestaurantService} from "../../../../providers/restaurant.service";

@Component({
  selector: 'qa-form-restaurant',
  templateUrl: './form-restaurant.component.html',
  styleUrls: ['./form-restaurant.component.scss']
})
export class FormRestaurantComponent {

  @Input() restaurant:Partial<Restaurant> = {};

  formRestaurant:FormGroup;
  feedbackMessage = "";

  constructor(private fb:FormBuilder, private restaurantService:RestaurantService) {
    this.formRestaurant = fb.group({
      description:["",[]],
      convives:["",[]],
    })
  }

  get convives(){
    return this.formRestaurant.get("convives")
  }

  get description(){
    return this.formRestaurant.get("description");
  }

  changeRestaurant() {
    if(this.formRestaurant.valid){
      if(this.description?.value != "") this.restaurant.description = this.description?.value;
      if(this.convives?.value != undefined) this.restaurant.maxConvivesAutorises = this.convives?.value;
      this.restaurantService.patchRestaurantAPI(this.restaurant, localStorage.getItem("token")).subscribe({
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
