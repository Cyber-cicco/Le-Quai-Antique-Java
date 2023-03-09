import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainMenuComponent } from './main-menu/main-menu.component';
import {RouterLink, RouterLinkActive} from "@angular/router";
import { FooterComponent } from './footer/footer.component';



@NgModule({
    declarations: [
        MainMenuComponent,
        FooterComponent
    ],
    exports: [
        MainMenuComponent,
        FooterComponent
    ],
  imports: [
    CommonModule,
    RouterLink,
    RouterLinkActive
  ]
})
export class SharedModule { }
