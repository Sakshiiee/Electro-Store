package com.lcwd.electronic.store.ElectronicStores.repositories;

import com.lcwd.electronic.store.ElectronicStores.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
