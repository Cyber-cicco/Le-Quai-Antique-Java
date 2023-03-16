import {Plat} from "./plat";

export interface Formule {

  nomFormule:string,
  description:string,
  prix:number,
  plats:Plat[],
  menu:string;
}
