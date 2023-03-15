import {Component, OnInit} from '@angular/core';
import {UserService} from "../../providers/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'qa-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit{
  active = 1;

  constructor(private userService:UserService, private router:Router) {

  }

  ngOnInit(): void {
    this.userService.checkIfAdmin(localStorage.getItem("token")).subscribe({
      next: ()=>{},
      error: ()=>{this.router.navigate(["/accueil"])}
    })
  }
}
