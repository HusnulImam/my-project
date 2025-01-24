package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.dto.CategoryDTO;
import com.springboot.pointofsales.entity.Category;
import com.springboot.pointofsales.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Endpoint khusus admin untuk meng-input data kategori
    @PostMapping("/category")
    public Category createCategory(@RequestBody Category category){
        return categoryService.inputCategory(category);
    }

    //Endpoint khusus admin untuk menghapus data kategori
    @DeleteMapping("/category")
    public String deleteCategory(@RequestParam Long id){
        categoryService.deleteCategory(id);
        return "Category successfully deleted";
    }

    //Endpoint khusus admin untuk mengubah data kategori
    @PutMapping("/category")
    public Category updateCategory(
            @RequestParam Long id,
            @RequestBody CategoryDTO categoryDTO
    ){
        return categoryService.updateCategory(id, categoryDTO);
    }
}
