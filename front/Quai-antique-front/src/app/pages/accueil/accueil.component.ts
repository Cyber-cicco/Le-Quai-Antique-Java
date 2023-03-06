import {Component, OnInit} from '@angular/core';
import {EnvService} from "../../providers/env.service";
import {Plat} from "../../models/plat";
import {PlatService} from "../../providers/plat.service";

@Component({
  selector: 'qa-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent  implements OnInit{

  URL_API:string

  plats:Plat[] = [];
  currentPlatIdx = 0;
  constructor(private env:EnvService, private platsSrv:PlatService) {
    this.URL_API = env.SERVER_URL;

  }

  changeIndex(number: number) {
    this.currentPlatIdx = (this.currentPlatIdx + number)%this.plats.length;
  }

  ngOnInit(): void {
    this.platsSrv.getAllPlatsAPI().subscribe(value => {
      this.plats = value;
    })
  }
}
