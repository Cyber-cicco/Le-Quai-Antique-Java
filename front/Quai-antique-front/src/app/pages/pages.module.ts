import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConnexionComponent } from './connexion/connexion.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { InfosComponent } from './infos/infos.component';
import { CreationCompteComponent } from './creation-compte/creation-compte.component';



@NgModule({
  declarations: [
    ConnexionComponent,
    InfosComponent,
    CreationCompteComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class PagesModule { }
