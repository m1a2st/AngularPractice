import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/service/product.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  currentCategoryId: number = 1;
  currentCategoryName = '';
  searchMode: boolean = false;

  //This current active route that loaded the component. Useful for accessing route parameters.
  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}

  //similar to @PostConstruct
  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  listProducts() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.handleSearchtProducts();
    } else {
      this.handleListProducts();
    }
  }

  handleSearchtProducts() {
    const theKeyword: string = this.route.snapshot.paramMap.get('keyword')!;

    //now search for the product usingkeyword
    this.productService.searchProducts(theKeyword).subscribe((data) => {
      this.products = data;
    });
  }

  handleListProducts() {
    //check if "id" parameter is available
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
    const hasCategoryName: boolean = this.route.snapshot.paramMap.has('name');

    if (hasCategoryId) {
      //get the "id" param string. convert string to a number using the "+" symbol
      //This is the not-null assertion operator. Tell compiler that the object is not null.
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
      this.currentCategoryName = this.route.snapshot.paramMap.get('name')!;
    } else {
      //not category id available ... default to category id 1
      this.currentCategoryId = 1;
      this.currentCategoryName = '';
    }

    //Method is invoke once you "subscribe"
    //now get the products for the given category id
    this.productService
      .getProductList(this.currentCategoryId)
      .subscribe((data) => {
        //Assign results to the Product array
        this.products = data;
      });
  }
}
