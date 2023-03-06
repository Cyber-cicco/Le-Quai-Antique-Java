import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ConnexionComponent} from "./pages/connexion/connexion.component";
import {CreationCompteComponent} from "./pages/creation-compte/creation-compte.component";
import {InfosComponent} from "./pages/infos/infos.component";

const routes: Routes = [
  {path : "profil/connexion", component: ConnexionComponent},
  {path: 'profil/creation', component: CreationCompteComponent},
  {path: 'profil/infos', component: InfosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
