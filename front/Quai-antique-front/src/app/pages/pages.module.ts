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
import { ModalReservationComponent } from './reserver/modal-reservation/modal-reservation.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import {SharedModule} from "../shared/shared.module";
import { MenuComponent } from './admin-dashboard/menu/menu.component';
import { PlatComponent } from './admin-dashboard/plat/plat.component';
import { RestaurantComponent } from './admin-dashboard/restaurant/restaurant.component';
import { FormulesComponent } from './admin-dashboard/formules/formules.component';
import { FormPlatComponent } from './admin-dashboard/plat/form-plat/form-plat.component';
import { FormMenuComponent } from './admin-dashboard/menu/form-menu/form-menu.component';
import { FormFormuleComponent } from './admin-dashboard/formules/form-formule/form-formule.component';



@NgModule({
  declarations: [
    ConnexionComponent,
    InfosComponent,
    CreationCompteComponent,
    AccueilComponent,
    CarteComponent,
    ReserverComponent,
    ModalReservationComponent,
    AdminDashboardComponent,
    MenuComponent,
    PlatComponent,
    RestaurantComponent,
    FormulesComponent,
    FormPlatComponent,
    FormMenuComponent,
    FormFormuleComponent,
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
        NgbCarouselModule,
        SharedModule
    ]
})
export class PagesModule { }
