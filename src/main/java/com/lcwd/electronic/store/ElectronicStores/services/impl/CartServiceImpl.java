package com.lcwd.electronic.store.ElectronicStores.services.impl;

import com.lcwd.electronic.store.ElectronicStores.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.ElectronicStores.dtos.CartDto;
import com.lcwd.electronic.store.ElectronicStores.entities.Cart;
import com.lcwd.electronic.store.ElectronicStores.entities.CartItem;
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
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Resource not found in database !!"));
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found in database!!"));

//            Cart cart = cartRepository.findByUser(user).get();
        Cart cart = null;

        try {
            cart = cartRepository.findByUser(user).get();
        } catch (NoSuchElementException e) {
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }
        //perform cart operation
        //if items are already avaialable then we only increase their quantity: update
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        List<CartItem> updatedItem = items.stream().map(item ->{

            if(item.getProduct().getProductId().equals(productId)){
                //item already present in cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity*product.getPrice());
                updated.set(true);

            }
            return item;
        }).collect(Collectors.toList());

        cart.setItems(updatedItem);


        //create items
        if(!updated.get()){
        CartItem cartItem = CartItem.builder()
                            .quantity(quantity)
                            .totalPrice(quantity*product.getPrice())
                            .cart(cart)
                            .product(product)
                            .build();

        cart.getItems().add(cartItem);

        cart.setUser(user);
        Cart updatedCart =  cartRepository.save(cart);

        return mapper.map(updatedCart,CartDto.class);

    }







    @Override
    public void removeItemFromCart(String userId, int cartItem) {

    }

    @Override
    public void cleatCart(String userId) {

    }
}
