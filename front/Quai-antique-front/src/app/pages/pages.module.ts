import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConnexionComponent } from './connexion/connexion.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { InfosComponent } from './infos/infos.component';
import { CreationCompteComponent } from './creation-compte/creation-compte.component';
import {RouterLink, RouterLinkActive} from "@angular/router";
import { AccueilComponent } from './accueil/accueil.component';



@NgModule({
  declarations: [
    ConnexionComponent,
    InfosComponent,
    CreationCompteComponent,
    AccueilComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterLink,
    RouterLinkActive,
  ]
})
export class PagesModule { }
