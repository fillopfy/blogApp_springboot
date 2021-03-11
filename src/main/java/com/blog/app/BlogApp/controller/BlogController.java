package com.blog.app.BlogApp.controller;

import com.blog.app.BlogApp.entity.Blog;
import com.blog.app.BlogApp.entity.User;
import com.blog.app.BlogApp.service.BlogService;
import com.blog.app.BlogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;


    /* Get Mappings */

    @GetMapping("/")
    public String home(){
        return "You are Connected";
    }

    @GetMapping("/getblogs")
    public List<Blog> getAllBlogs(){
        return blogService.getAllBlogs();
    }

    //get user for given blogId
    @GetMapping("/getuserforblog/blogid/{id}")
    public User getUserForBlog(@PathVariable("id") Long blodId){
        return blogService.getUserForBlog(blodId);
    }

    //Get blog for given commentId
    @GetMapping("/getblog/forcomment/{id}")
    public Blog getBlogForComment(@PathVariable("id") Long commentId){
        return blogService.getBlogForComment(commentId);
    }

    //Get blog for particular category id
    @GetMapping("/getblog/forcategory/{id}")
    public Blog getBlogForCategory(@PathVariable("id") Long categoryId){
        return blogService.getBlogForCategory(categoryId);
    }




    /* Post Mappings */

    //add blog
    @PostMapping("/addblog")
    public String addBlog(@RequestBody Blog blog){
        return blogService.addBlog(blog);
    }

    //assigning user to blog with userId as id
    @PostMapping("/assignusertoblog/userid/{id}")
    public String assigningUserToBlog(@PathVariable("id") Long userId, @RequestBody Blog blog){
        return userService.assigningUserToBlog(userId, blog);
    }




    /* Put Mappings */

    @PutMapping("/updateblog/blogid/{id}")
    public String updateBlog(@PathVariable("id") Long id, @RequestBody Blog blog){
        return blogService.updateBlog(id, blog);
    }



    /* Delete Mappings */

    @DeleteMapping("/deleteblog/blogid/{id}")
    public String deleteBlog(@PathVariable("id") Long id){
        return blogService.deleteBlog(id);
    }
}
