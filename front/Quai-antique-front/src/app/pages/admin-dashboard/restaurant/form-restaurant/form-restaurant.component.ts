import {Component, Input} from '@angular/core';
import {Restaurant} from "../../../../models/restaurant";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'qa-form-restaurant',
  templateUrl: './form-restaurant.component.html',
  styleUrls: ['./form-restaurant.component.scss']
})
export class FormRestaurantComponent {

  @Input() restaurant:Partial<Restaurant> = {};

  formRestaurant:FormGroup;

  constructor(private fb:FormBuilder) {
    this.formRestaurant = fb.group({
      description:["",[Validators.required]],
      convives:["",[Validators.required]],
    })
  }
}
