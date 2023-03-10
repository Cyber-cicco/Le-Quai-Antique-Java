import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConnexionComponent } from './connexion/connexion.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { InfosComponent } from './infos/infos.component';
import { CreationCompteComponent } from './creation-compte/creation-compte.component';
import {RouterLink, RouterLinkActive} from "@angular/router";
import { AccueilComponent } from './accueil/accueil.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgbCarouselModule, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { CarteComponent } from './carte/carte.component';
import { ReserverComponent } from './reserver/reserver.component';



@NgModule({
  declarations: [
    ConnexionComponent,
    InfosComponent,
    CreationCompteComponent,
    AccueilComponent,
    CarteComponent,
    ReserverComponent,
  ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        RouterLink,
        RouterLinkActive,
        BrowserAnimationsModule,
        NgbModule,
        NgbCarouselModule
    ]
})
export class PagesModule { }
