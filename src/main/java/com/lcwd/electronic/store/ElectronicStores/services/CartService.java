package com.lcwd.electronic.store.ElectronicStores.services;

import com.lcwd.electronic.store.ElectronicStores.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.ElectronicStores.dtos.CartDto;

public interface CartService {

    //add item to cart:
    //case 1: cart for user is not available: we will create the cart
    //case2: cart available add item to cart

    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    //remove item from cart:
    void removeItemFromCart(String userId, int cartItem);

    //remove all items from cart
    void cleatCart(String userId);

}
