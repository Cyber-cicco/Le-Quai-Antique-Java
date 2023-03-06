import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvService {

  SERVER_URL = "http://localhost:8080"
  constructor() { }
}
