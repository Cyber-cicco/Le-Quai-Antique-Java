import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainMenuComponent } from './main-menu/main-menu.component';
import {RouterLink, RouterLinkActive} from "@angular/router";



@NgModule({
    declarations: [
        MainMenuComponent
    ],
    exports: [
        MainMenuComponent
    ],
  imports: [
    CommonModule,
    RouterLink,
    RouterLinkActive
  ]
})
export class SharedModule { }
