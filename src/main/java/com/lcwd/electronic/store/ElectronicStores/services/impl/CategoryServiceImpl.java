package com.lcwd.electronic.store.ElectronicStores.services.impl;

import com.lcwd.electronic.store.ElectronicStores.dtos.CategoryDto;
import com.lcwd.electronic.store.ElectronicStores.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStores.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        return null;
    }

    @Override
    public void delete(String categoryId) {

    }

    @Override
    public PageableResponse<CategoryDto> getAll() {
        return null;
    }

    @Override
    public CategoryDto get(String categoryId) {
        return null;
    }
}
