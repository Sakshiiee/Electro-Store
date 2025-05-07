package com.lcwd.electronic.store.ElectronicStores.services.impl;

import com.lcwd.electronic.store.ElectronicStores.dtos.ProductDto;
import com.lcwd.electronic.store.ElectronicStores.entities.Product;
import com.lcwd.electronic.store.ElectronicStores.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStores.repositories.ProductReository;
import com.lcwd.electronic.store.ElectronicStores.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductReository productReository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        Product savedProduct = productReository.save(product);
        return mapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        //fetch the product of given id
        Product product = productReository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found of given id !!"));
        productDto.setTitle(productDto.getTitle());
        productDto.setDescription(productDto.getDescription());
        productDto.setPrice(productDto.getPrice());
        productDto.setDiscountedPrice(productDto.getDiscountedPrice());
        productDto.setQuantity(productDto.getQuantity());
        productDto.setLive(productDto.isLive());
        productDto.setStock(productDto.isStock());

        Product updatedProduct = productReository.save(product);
        return mapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void delete(String productId) {

    }

    @Override
    public ProductDto get(String productDto) {
        return null;
    }

    @Override
    public List<ProductDto> getAll() {
        return List.of();
    }

    @Override
    public List<ProductDto> getAllLive() {
        return List.of();
    }

    @Override
    public List<ProductDto> searchByTitle(String subTitle) {
        return List.of();
    }
}
