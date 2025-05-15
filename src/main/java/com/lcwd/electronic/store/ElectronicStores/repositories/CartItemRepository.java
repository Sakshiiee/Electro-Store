package com.lcwd.electronic.store.ElectronicStores.repositories;

import com.lcwd.electronic.store.ElectronicStores.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
