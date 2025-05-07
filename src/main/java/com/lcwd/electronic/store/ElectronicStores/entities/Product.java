package com.lcwd.electronic.store.ElectronicStores.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    private String productId;

    private String title;

    @Column(length = 10000)
    private String description;

    private int price;

    private int discountedPrice;

    private int quantity;

    private Date addedDate;                  //when product is add

    private boolean live;                    // when live value TRUE then this product will live if it is FALSE  if live = true it avaialble

    private boolean stock;                   // if admin do FALSE then it will out of stock






}
