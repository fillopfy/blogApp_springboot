package com.blog.app.BlogApp.service;

import com.blog.app.BlogApp.entity.Blog;
import com.blog.app.BlogApp.entity.User;
import com.blog.app.BlogApp.repository.BlogRepository;
import com.blog.app.BlogApp.repository.CategoryRepository;
import com.blog.app.BlogApp.repository.CommentRepository;
import com.blog.app.BlogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CommentRepository commentRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public String addBlog(Blog blog) {
        try{
            blogRepository.save(blog);
            return "Added";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String updateBlog(Long id, Blog blog) {
        try{
            Optional<Blog> originalBlog=blogRepository.findById(id);
            originalBlog.get().setBlogId(blog.getBlogId());
            originalBlog.get().setBlogTopic(blog.getBlogTopic());
            originalBlog.get().setBlogDescription(blog.getBlogDescription());
            blogRepository.save(originalBlog.get());
            return "Successfully updated.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String deleteBlog(Long id) {
        try{
            blogRepository.deleteById(id);
            return "Successfully deleted.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Transactional
    public User getUserForBlog(Long blogId) {
        try{
            return blogRepository.findById(blogId).get().getUser();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public Blog getBlogForCategory(Long categoryId) {
        try{
            return categoryRepository.findById(categoryId).get().getBlog();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public Blog getBlogForComment(Long commentId) {
        try{
            return commentRepository.findById(commentId).get().getBlog();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
