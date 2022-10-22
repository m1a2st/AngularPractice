import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/service/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-list',
  // templateUrl: './product-list.component.html',
  templateUrl: './product-list-table.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductService) {}

  //similar to @PostConstruct
  ngOnInit(): void {
    this.listProducts();
  }

  listProducts() {
    //Method is invoke once you "subscribe"
    this.productService.getProductList().subscribe((data) => {
      //Assign results to the Product array
      console.log(data);
      this.products = data;
    });
  }
}
