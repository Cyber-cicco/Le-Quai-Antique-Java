import { Component } from '@angular/core';
import {UserService} from "../../providers/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'qa-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.scss']
})
export class MainMenuComponent {
  menuHidden= true;
  isConnected = false;

  constructor(private userSrv:UserService, private router:Router) {
    this.userSrv.getIsConnectedSubjectAsObservable().subscribe(value =>{
      this.isConnected = value
      console.log(this.isConnected);
    });
  }

  showSubMenu() {
    this.menuHidden = !this.menuHidden;
    this.checkConnexion();
  }
  checkConnexion(){
    let token = localStorage.getItem('token');
    if(token){
      this.userSrv.checkConnexion(token).subscribe();
    }
  }
}
