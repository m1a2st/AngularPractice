import { ProductCategory } from './../common/product-category';
import { Product } from './../common/product';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = `${environment.luv2shopApiUrl}/products`;
  private categoryUrl = `${environment.luv2shopApiUrl}/product-category`;
  constructor(private httpClient: HttpClient) {}

  //Returns an observable.
  //Map ths JSON data from Spring Data REST to Product array
  getProductList(theCategoryId: number): Observable<Product[]> {
    //need to build URL based on category id ...
    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`;
    return this.getProducts(searchUrl);
  }
  //need to build URL based on category id, page. and size
  getProductListPaginate(
    thePage: number,
    thePageSize: number,
    theCategoryId: number
  ): Observable<GetResponseProducts> {
    const pageUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}&page=${thePage}&size=${thePageSize}`;
    return this.httpClient.get<GetResponseProducts>(pageUrl);
  }

  searchProductsPaginate(
    thePage: number,
    thePageSize: number,
    theKeyword: string
  ): Observable<GetResponseProducts> {
    // need to build URL based on keyword, page and size
    const searchUrl =
      `${this.baseUrl}/search/findByNameContaining?name=${theKeyword}` +
      `&page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<GetResponseProducts>(searchUrl);
  }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpClient
      .get<GetResponseProductsCategory>(this.categoryUrl)
      .pipe(map((response) => response._embedded.productCategory));
  }

  searchProducts(theKeyword: string): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${theKeyword}`;
    return this.getProducts(searchUrl);
  }

  private getProducts(searchUrl: string): Observable<Product[]> {
    return this.httpClient
      .get<GetResponseProducts>(searchUrl)
      .pipe(map((response) => response._embedded.product));
  }

  getProduct(theProductId: number): Observable<Product> {
    //need to build URL based on product id
    const productUrl = `${this.baseUrl}/${theProductId}`;
    return this.httpClient.get<Product>(productUrl);
  }
}

interface GetResponseProducts {
  _embedded: {
    product: Product[];
  };
  page: {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
  };
}

interface GetResponseProductsCategory {
  _embedded: {
    productCategory: ProductCategory[];
  };
}
