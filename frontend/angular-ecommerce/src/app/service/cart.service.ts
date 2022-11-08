import { CartItem } from './../common/cart-item';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  constructor() {}

  addToCart(theCartItem: CartItem) {
    //check if we already have the item in our cart
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem | undefined = undefined;

    if (this.cartItems.length > 0) {
      //find the item in the cart based on item id
      existingCartItem = this.cartItems.find(
        (tempCartItem) => tempCartItem.id === theCartItem.id
      );
      //check if we found it
      alreadyExistsInCart = existingCartItem != undefined;
    }

    if (alreadyExistsInCart) {
      //increment the quanitity
      existingCartItem!.quantity++;
    } else {
      //just add the item to the array
      this.cartItems.push(theCartItem);
    }

    //compute cart total price and total quantity
    this.computeCartTotal();
  }

  computeCartTotal() {
    let totalPriceValue: number = 0;
    let totalQuanityValue: number = 0;
    for (let tempCartItem of this.cartItems) {
      totalPriceValue += tempCartItem.unitPrice * tempCartItem.quantity;
      totalQuanityValue += tempCartItem.quantity;
    }
    //public the new values ... all subscribes will receive data
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuanityValue);

    //log cart data just for debugging purpose
    this.logCartData(totalPriceValue, totalQuanityValue);
  }

  logCartData(totalPriceValue: number, totalQuanityValue: number) {
    for (let tempCartItem of this.cartItems) {
      const subTotalPrice = tempCartItem.quantity * tempCartItem.unitPrice;
      console.log(
        `name : ${tempCartItem.name}, quantity : ${tempCartItem.quantity}, unitPrice : ${tempCartItem.unitPrice}, subTotalPrice : ${subTotalPrice}`
      );
    }
    console.log(
      `totalPriceValue = ${totalPriceValue.toFixed(
        2
      )}, totalQuanityValue = ${totalQuanityValue}`
    );
    console.log('----------');
  }

  decrementQuantity(theCartItem: CartItem) {
    theCartItem.quantity--;
    if (theCartItem.quantity === 0) {
      this.remove(theCartItem);
    } else {
      this.computeCartTotal();
    }
  }

  remove(theCartItem: CartItem) {
    //get index of item in the array
    const itemIndex = this.cartItems.findIndex(
      (tempCartItem) => theCartItem.id === tempCartItem.id
    );
    //if found,remove the item from the array at the given index
    if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);
      this.computeCartTotal();
    }
  }
}
