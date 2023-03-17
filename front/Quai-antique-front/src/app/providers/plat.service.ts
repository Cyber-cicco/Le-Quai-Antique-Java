import { Injectable } from '@angular/core';
import {EnvService} from "./env.service";
import {HttpClient} from "@angular/common/http";
import {Plat} from "../models/plat";
import {Formule} from "../models/formule";

@Injectable({
  providedIn: 'root'
})
export class PlatService {

  private URL_LIST_PLATS:string;
  private URL_PATCH_PLAT: string;

  private URL_PHOTO_PATCH:string;
  constructor(private env:EnvService, private http:HttpClient) {
    this.URL_LIST_PLATS = env.SERVER_URL + "/plats/list";
    this.URL_PATCH_PLAT = env.SERVER_URL + "/admin/plat";
    this.URL_PHOTO_PATCH = env.SERVER_URL + "/admin/photo?id=";
  }


  getAllPlatsAPI(){
    return this.http.get<Plat[]>(this.URL_LIST_PLATS);
  }

  patchPlatAPI(plat: Partial<Plat>, token: string | null) {
    return this.http.patch<{message:string, formule:Formule}>(this.URL_PATCH_PLAT + "?nom=" + token, plat,{headers:{
        "Authorization": "Bearer "+token,
        "Content-type": "application/json"}});
  }

  postPhotoAPI(formData: FormData, id: number | undefined, token: string | null) {
    return this.http.post(this.URL_PHOTO_PATCH + id, formData, {headers:{
        "Authorization": "Bearer "+token}});
  }
}
