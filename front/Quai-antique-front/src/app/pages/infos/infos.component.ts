import { Component } from '@angular/core';

@Component({
  selector: 'qa-infos',
  templateUrl: './infos.component.html',
  styleUrls: ['./infos.component.scss']
})
export class InfosComponent {
  token= localStorage.getItem("token");

}
