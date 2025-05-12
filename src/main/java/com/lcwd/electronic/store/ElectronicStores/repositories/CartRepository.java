package com.lcwd.electronic.store.ElectronicStores.repositories;

import com.lcwd.electronic.store.ElectronicStores.entities.Cart;
import com.lcwd.electronic.store.ElectronicStores.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {


    Optional<Cart> findByUser(User user);
}
