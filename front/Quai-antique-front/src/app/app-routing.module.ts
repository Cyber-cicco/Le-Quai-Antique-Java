import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ConnexionComponent} from "./pages/connexion/connexion.component";
import {CreationCompteComponent} from "./pages/creation-compte/creation-compte.component";
import {InfosComponent} from "./pages/infos/infos.component";
import {AccueilComponent} from "./pages/accueil/accueil.component";
import {CarteComponent} from "./pages/carte/carte.component";
import {ReserverComponent} from "./pages/reserver/reserver.component";
import {AdminDashboardComponent} from "./pages/admin-dashboard/admin-dashboard.component";

const routes: Routes = [
  {path : "profil/connexion", component: ConnexionComponent},
  {path: 'profil/creation', component: CreationCompteComponent},
  {path: 'profil/infos', component: InfosComponent},
  {path: 'accueil', component: AccueilComponent},
  {path: 'carte', component: CarteComponent},
  {path: 'reserver', component: ReserverComponent},
  {path: '9fc561fbf4c7028b2c2ab3662b2602e78658eecba8a6bfacf50c3de57f66202a', component: AdminDashboardComponent},
  {path: '', component: AccueilComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
