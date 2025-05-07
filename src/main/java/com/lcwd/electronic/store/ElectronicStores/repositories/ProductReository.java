package com.lcwd.electronic.store.ElectronicStores.repositories;

import com.lcwd.electronic.store.ElectronicStores.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReository extends JpaRepository<Product,String> {

    //SEARCH
    List<Product> findByTitleContaining(String subTitle);

    List<Product> findByLiveTrue();                 // shows all products which are live


}
