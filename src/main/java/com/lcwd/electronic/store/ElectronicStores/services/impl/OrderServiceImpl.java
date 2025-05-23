package com.lcwd.electronic.store.ElectronicStores.services.impl;

import com.lcwd.electronic.store.ElectronicStores.dtos.CreateOrderRequest;
import com.lcwd.electronic.store.ElectronicStores.dtos.OrderDto;
import com.lcwd.electronic.store.ElectronicStores.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStores.entities.*;
import com.lcwd.electronic.store.ElectronicStores.exceptions.BadApiRequestException;
import com.lcwd.electronic.store.ElectronicStores.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStores.helper.Helper;
import com.lcwd.electronic.store.ElectronicStores.repositories.CartRepository;
import com.lcwd.electronic.store.ElectronicStores.repositories.OrderRepository;
import com.lcwd.electronic.store.ElectronicStores.repositories.UserRepository;
import com.lcwd.electronic.store.ElectronicStores.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepository cartRepository;

//    @Override
//    public OrderDto createOrder(CreateOrderRequest orderDto) {
//        String userId = orderDto.getUserId();
//        String cartId = orderDto.getCartId();
//        //fetch user
//        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with given id !!"));
//
//        //fetch cart
//        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart with given id not found on server !!"));
//
//        List<CartItem> cartItems = cart.getItems();
//
//        if(cartItems.size()<= 0){
//            throw new BadApiRequestException("Invalid numbers of items in cart !!");
//        }
//
//        //other checks
//
//        Order order = Order.builder()
//                .billingName(orderDto.getBillingName())
//                .billingPhone(orderDto.getBillingPhone())
//                .billingAddress(orderDto.getBillingAddress())
//                .orderedDate(new Date())
//                .delivereddDate(null)
//                .paymentStatus(orderDto.getPaymentStatus())
//                .orderStatus(orderDto.getOrderStatus())
//                .orderId(UUID.randomUUID().toString())
//                .user(user)
//                .build();
//
//        //orderItems,amount
//        AtomicReference<Integer> orderAmount = new AtomicReference<>(0);
//        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
//            //convert all cartItems into orderItems
//            OrderItem orderItem = OrderItem.builder()
//                    .quantity(cartItem.getQuantity())
//                    .product(cartItem.getProduct())
//                    .totalPrice(cartItem.getQuantity()*cartItem.getProduct().getDiscountedPrice())
//                    .order(order)
//                    .build();
//            orderAmount.set(orderAmount.get()+ orderItem.getTotalPrice());
//            return orderItem();
//        }).collect(Collectors.toList());
//
//        order.setOrderItems(orderItems);
//        order.setOrderAmout(orderAmount.get());
//
//        //
//        cart.getItems().clear();
//        cartRepository.save(cart);
//        Order savedOrder = orderRepository.save(order);
//        return modelMapper.map(savedOrder,OrderDto.class);
//    }
//
//    @Override
//    public void removeOrder(String userId) {
//        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order is not found !!"));
//        orderRepository.delete(order);
//
//    }
@Override
public OrderDto createOrder(CreateOrderRequest orderDto) {

    String userId = orderDto.getUserId();
    String cartId = orderDto.getCartId();
    //fetch user
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));

    //fetch cart
    Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart with given id not found on server !!"));

    List<CartItem> cartItems = cart.getItems();

    if (cartItems.size() <= 0) {
        throw new BadApiRequestException("Invalid number of items in cart !!");

    }

    //other checks

    Order order = Order.builder()
            .billingName(orderDto.getBillingName())
            .billingPhone(orderDto.getBillingPhone())
            .billingAddress(orderDto.getBillingAddress())
            .orderedDate(new Date())
            .delivereddDate(null)
            .paymentStatus(orderDto.getPaymentStatus())
            .orderStatus(orderDto.getOrderStatus())
            .orderId(UUID.randomUUID().toString())
            .user(user)
            .build();

//        orderItems,amount

    AtomicReference<Integer> orderAmount = new AtomicReference<>(0);
    List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
//            CartItem->OrderItem
        OrderItem orderItem = OrderItem.builder()
                .quantity(cartItem.getQuantity())
                .product(cartItem.getProduct())
                .totalPrice(cartItem.getQuantity() * cartItem.getProduct().getDiscountedPrice())
                .order(order)
                .build();

        orderAmount.set(orderAmount.get() + orderItem.getTotalPrice());
        return orderItem;
    }).collect(Collectors.toList());


    order.setOrderItems(orderItems);
    order.setOrderAmout(orderAmount.get());

    System.out.println(order.getOrderItems().size());

    //
    cart.getItems().clear();
    cartRepository.save(cart);
    Order savedOrder = orderRepository.save(order);
    return modelMapper.map(savedOrder, OrderDto.class);
}

    @Override
    public void removeOrder(String orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order is not found !!"));
        orderRepository.delete(order);

    }

    @Override
    public List<OrderDto> getOrdersOfUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found !!"));
        List<Order> orders = orderRepository.findByUser(user);
        List<OrderDto> orderDtos = orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Order> page = orderRepository.findAll(pageable);
        return Helper.getPageableResponse(page, OrderDto.class);
    }
}
