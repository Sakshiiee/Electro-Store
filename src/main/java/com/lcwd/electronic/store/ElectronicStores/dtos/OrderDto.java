package com.lcwd.electronic.store.ElectronicStores.dtos;

import com.lcwd.electronic.store.ElectronicStores.entities.OrderItem;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderDto {

    private String orderId;
    private String orderStatus;
    private String paymentStatus;
    private int orderAmount;
    private String billingAddress;
    private String billingPhone;
    private String billingName;
    private Date  orderedDate = new Date();
    private Date deliveredDate;
    private UserDto use;
    private List<OrderItemDto> orderItems = new ArrayList<>();

}
