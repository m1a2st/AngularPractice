import { Product } from './../common/product';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/api/product';
  constructor(private httpClient: HttpClient) {}

  //Returns an observable.
  //Map ths JSON data from Spring Data REST to Product array
  getProductList(): Observable<Product[]> {
    return this.httpClient
      .get<GetResponse>(this.baseUrl)
      .pipe(map((response) => response._embedded.product));
  }
}

interface GetResponse {
  _embedded: {
    product: Product[];
  };
}
