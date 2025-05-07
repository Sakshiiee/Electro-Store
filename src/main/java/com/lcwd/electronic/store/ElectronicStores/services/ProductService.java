package com.lcwd.electronic.store.ElectronicStores.services;

import com.lcwd.electronic.store.ElectronicStores.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);

    //update
    ProductDto update(ProductDto productDto, String productId);

    //delete
    void delete(String productId);

    //get single product
    ProductDto get(String productDto);

    //get all product
    List<ProductDto> getAll();

    //get all : live
    List<ProductDto> getAllLive();

    //search product
    List<ProductDto> searchByTitle(String subTitle);
}
