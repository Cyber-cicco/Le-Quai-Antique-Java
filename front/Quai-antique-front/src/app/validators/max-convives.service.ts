import { Injectable } from '@angular/core';
import {AbstractControl, AsyncValidator, ValidationErrors} from "@angular/forms";
import {map, Observable} from "rxjs";
import {RestaurantService} from "../providers/restaurant.service";

@Injectable({
  providedIn: 'root'
})
export class MaxConvivesService implements AsyncValidator {

  constructor(private restaurantService:RestaurantService) {
  }
  registerOnValidatorChange(fn: () => void): void {
  }

  validate(control: AbstractControl):Observable<ValidationErrors | null>{
    return this.restaurantService.getRestaurant().pipe(
      map((response) => {
        return response.maxConvivesAutorises < control.value ? { error: "Too much convives" } : null;
      }))}
}
