import { Component } from '@angular/core';
import {EnvService} from "../../providers/env.service";

@Component({
  selector: 'qa-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent {

  URL_API:string
  constructor(private env:EnvService) {
    this.URL_API = env.SERVER_URL;
  }
}
