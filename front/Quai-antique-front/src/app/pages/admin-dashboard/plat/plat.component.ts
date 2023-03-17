import { Component } from '@angular/core';
import {Plat} from "../../../models/plat";
import {PlatService} from "../../../providers/plat.service";
import {UserService} from "../../../providers/user.service";

@Component({
  selector: 'qa-plat',
  templateUrl: './plat.component.html',
  styleUrls: ['./plat.component.scss']
})
export class PlatComponent {

  plats:Plat[] = []

  allergies:string[] = []

  constructor(private platService:PlatService) {
    this.platService.getAllPlatsAPI().subscribe(value => {
      this.plats = value;
    });
  }
}
