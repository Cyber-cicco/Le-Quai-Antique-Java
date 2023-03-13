import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateUtilService {

  changeJavaTimeToJSHour(time:string){
    return [time.substring(0, time.indexOf(":")), time.substring(time.indexOf(":")+1, time.lastIndexOf(":")), time.substring(time.lastIndexOf(":")+1)]
      .map(value => Number.parseInt(value));
  }
  constructor() { }
}
