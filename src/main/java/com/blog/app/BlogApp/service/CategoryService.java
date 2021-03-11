package com.blog.app.BlogApp.service;

import com.blog.app.BlogApp.entity.Blog;
import com.blog.app.BlogApp.entity.Category;
import com.blog.app.BlogApp.repository.BlogRepository;
import com.blog.app.BlogApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public String addCategory(Category category) {
        try{
            categoryRepository.save(category);
            return "Added";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String updateCategory(Long id, Category category) {
        try{
            Optional<Category> originalCategory=categoryRepository.findById(id);
            originalCategory.get().setCategoryId(category.getCategoryId());
            originalCategory.get().setCategoryName(category.getCategoryName());
            categoryRepository.save(originalCategory.get());
            return "Successfully updated.";
        }catch (Exception e){
            return  e.getMessage();
        }
    }

    public String deleteCategory(Long id) {
        try{
            categoryRepository.deleteById(id);
            return "Successfully deleted.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Transactional
    public String assignCategoryToBlog(Long blogId, Category category) {
        try{
            Blog blog=blogRepository.findById(blogId).get();
            category.setBlog(blog);
            categoryRepository.save(category);
            blog.addCategory(category);
            blogRepository.save(blog);
            return "Successfully added "+blog.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Transactional
    public List<Category> getAllCategoryForGivenBlog(Long blogId) {
        try{
            return blogRepository.findById(blogId).get().getCategories();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
