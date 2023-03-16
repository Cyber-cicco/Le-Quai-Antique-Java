import { Injectable } from '@angular/core';
import {MenuService} from "../providers/menu.service";
import {AbstractControl, AsyncValidator, ValidationErrors} from "@angular/forms";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MenuNotTakenValidatorService implements AsyncValidator{

  constructor(private menuService:MenuService) { }

  registerOnValidatorChange(fn: () => void): void {
  }

  validate(control: AbstractControl):Observable<ValidationErrors | null>{
    return this.menuService.isNomMenuTaken(control.value, localStorage.getItem("token")).pipe(
      map((response) => {
        return response.exists ? { error: "Already exists" } : null;
      }))}
}
