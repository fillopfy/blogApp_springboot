package com.blog.app.BlogApp.service;

import com.blog.app.BlogApp.entity.Blog;
import com.blog.app.BlogApp.entity.Comment;
import com.blog.app.BlogApp.repository.BlogRepository;
import com.blog.app.BlogApp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BlogRepository blogRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public String addComment(Comment comment) {
        try{
            commentRepository.save(comment);
            return "Added.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String updateComment(Long id, Comment comment) {
        try{
            Optional<Comment> original=commentRepository.findById(id);
            original.get().setCommentId(comment.getCommentId());
            original.get().setCommentContent(comment.getCommentContent());
            return "Updated.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String deleteComment(Long id) {
        try{
            commentRepository.deleteById(id);
            return "Deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    //Assigning comment for particular blog
    @Transactional
    public String assignCommentForBlog(Long blogId, Comment comment) {
        try{
            Blog blog=blogRepository.findById(blogId).get();
            comment.setBlog(blog);
            commentRepository.save(comment);
            blog.addComment(comment);
            blogRepository.save(blog);
            return "Successfully added"+" "+blog.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Transactional
    public List<Comment> getAllCommentsForGivenBlog(Long blogId) {
        try{
            return blogRepository.findById(blogId).get().getComments();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public String addMultipleCommentsForGivenBlog(Long blogId, List<Comment> comments) {
        try{
            Blog blog=blogRepository.findById(blogId).get();
            for(Comment comment:comments){
                comment.setBlog(blog);
                commentRepository.save(comment);
                blog.addComment(comment);
            }
            blogRepository.save(blog);
            return "Added all comments"+" "+blog.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
