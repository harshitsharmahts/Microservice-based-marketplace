import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class InventoryService {

    constructor(
        private http: HttpClient) { }

    getItems() {
      return this.http.get('http://18.210.39.123:8080/api/inventory/0/10');
    }
}
