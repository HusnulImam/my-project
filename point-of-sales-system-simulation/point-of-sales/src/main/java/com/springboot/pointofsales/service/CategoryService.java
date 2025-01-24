package com.springboot.pointofsales.service;

import com.springboot.pointofsales.dto.CategoryDTO;
import com.springboot.pointofsales.entity.Category;
import com.springboot.pointofsales.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category inputCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        categoryRepository.delete(category);
        return category;
    }

    public Category updateCategory(Long id, CategoryDTO categoryDTO){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Category not Found"));

        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return category;
    }
}
