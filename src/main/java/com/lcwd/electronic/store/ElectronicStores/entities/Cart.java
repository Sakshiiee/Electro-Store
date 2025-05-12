//which user has which cart

package com.lcwd.electronic.store.ElectronicStores.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private String cartId;

    private Date  createdAt;

    //this cart is belong to which user
    @OneToOne
    private User user;

    //mapping cart-item entity
    //one cart has many items
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

}
