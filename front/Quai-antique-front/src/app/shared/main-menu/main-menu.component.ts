import { Component } from '@angular/core';
import {UserService} from "../../providers/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'qa-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.scss']
})
export class MainMenuComponent {
  menuPhoneHidden= true;
  isConnected = false;
  menuProfilHidden = true;

  constructor(private userSrv:UserService, private router:Router) {
    this.userSrv.getIsConnectedSubjectAsObservable().subscribe(value =>{
      this.isConnected = value
      this.userSrv.isConnected = value;
    });
    this.checkConnexion();
  }

  showSubMenu() {
    this.menuPhoneHidden = !this.menuPhoneHidden;
    this.checkConnexion();
  }
  checkConnexion(){
    let token = localStorage.getItem('token');
    if(token){
      this.userSrv.checkConnexion(token).subscribe({
        next:()=>{
          this.userSrv.getIsConnectedSubject().next(true);
          if(Object.keys(this.userSrv.user).length == 0)this.userSrv.getUserInfosAPI(localStorage.getItem("token"))
            .subscribe(value => this.userSrv.getUserSubject().next(value));
        },
        error:()=>{
          this.userSrv.getIsConnectedSubject().next(false);
          localStorage.clear();
        }
      });
    } else {
      this.userSrv.getIsConnectedSubject().next(false);
    }
  }

  disconnect() {
    localStorage.clear();
    this.userSrv.getIsConnectedSubject().next(false);
    this.hideMenus();
    this.userSrv.getUserSubject().next({});
    this.router.navigate(['/accueil']);
  }

  showProfileMenu() {
    this.menuProfilHidden = !this.menuProfilHidden;
  }

  hideMenus() {
    this.menuProfilHidden = true;
    this.menuPhoneHidden = true;
  }
}
