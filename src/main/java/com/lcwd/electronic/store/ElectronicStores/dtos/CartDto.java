package com.lcwd.electronic.store.ElectronicStores.dtos;

import com.lcwd.electronic.store.ElectronicStores.entities.CartItem;
import com.lcwd.electronic.store.ElectronicStores.entities.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private String cartId;

    private Date createdAt;

    private UserDto user;

    private List<CartItemDto> items = new ArrayList<>();
}
