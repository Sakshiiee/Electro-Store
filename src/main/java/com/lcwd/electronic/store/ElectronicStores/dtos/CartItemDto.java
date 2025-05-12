package com.lcwd.electronic.store.ElectronicStores.dtos;

import com.lcwd.electronic.store.ElectronicStores.entities.Cart;
import com.lcwd.electronic.store.ElectronicStores.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    private int cartItemId;

    private ProductDto product;

    private int quantity;

    private int totalPrice;

}
