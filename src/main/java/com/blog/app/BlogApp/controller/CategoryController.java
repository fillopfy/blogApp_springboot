package com.blog.app.BlogApp.controller;

import com.blog.app.BlogApp.entity.Category;
import com.blog.app.BlogApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /* Get Mappings */

    @GetMapping("/getcategories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //Get list of category for particular blogId
    @GetMapping("/getcategoriesforblog/blogid/{id}")
    public List<Category> getAllCategoryForGivenBlog(@PathVariable("id") Long blogId){
        return categoryService.getAllCategoryForGivenBlog(blogId);
    }



    /* Post Mappings */

    @PostMapping("/addcategory")
    public String addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    //assigning category to blog with blogId as id in pathvariable
    @PostMapping("/assigncategorytoblog/blogid/{id}")
    public String assignCategoryToBlog(@PathVariable("id") Long blogId, @RequestBody Category category){
        return categoryService.assignCategoryToBlog(blogId, category);
    }



    /* Put Mappings */

    @PutMapping("/updatecategory/categoryid/{id}")
    public String updateCategory(@PathVariable("id") Long id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }



    /* Delete Mappings */

    @DeleteMapping("/deletecategory/categoryid/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        return categoryService.deleteCategory(id);
    }
}
