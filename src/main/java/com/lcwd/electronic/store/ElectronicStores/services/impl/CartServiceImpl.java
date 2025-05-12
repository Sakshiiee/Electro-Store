package com.lcwd.electronic.store.ElectronicStores.services.impl;

import com.lcwd.electronic.store.ElectronicStores.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.ElectronicStores.dtos.CartDto;
import com.lcwd.electronic.store.ElectronicStores.entities.Cart;
import com.lcwd.electronic.store.ElectronicStores.entities.Product;
import com.lcwd.electronic.store.ElectronicStores.entities.User;
import com.lcwd.electronic.store.ElectronicStores.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStores.repositories.CartRepository;
import com.lcwd.electronic.store.ElectronicStores.repositories.ProductRepository;
import com.lcwd.electronic.store.ElectronicStores.repositories.UserRepository;
import com.lcwd.electronic.store.ElectronicStores.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.NoSuchElementException;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
            int quantity = request.getQuantity();
            String productId = request.getProductId();
            //fetch the product
            Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Resource not found in database !!"));
            //fetch the user from db
            User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found in database!!"));

//            Cart cart = cartRepository.findByUser(user).get();
        Cart cart = null;

        try{
            cart = cartRepository.findByUser(user).get();
        } catch (NoSuchElementException e) {
          cart = new Cart();
        }






    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {

    }

    @Override
    public void cleatCart(String userId) {

    }
}
