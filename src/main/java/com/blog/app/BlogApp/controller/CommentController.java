package com.blog.app.BlogApp.controller;

import com.blog.app.BlogApp.entity.Comment;
import com.blog.app.BlogApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    /* Get Mappings */

    //Get all comments
    @GetMapping("/getcomments")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    //Get list of comments for given blog id
    @GetMapping("/getcommentsforblog/blogid/{id}")
    public List<Comment> getAllCommentsForGivenBlog(@PathVariable("id") Long blogId){
        return commentService.getAllCommentsForGivenBlog(blogId);
    }



    /* Post Mappings */

    //Post one comment
    @PostMapping("/addcomment")
    public String addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    //assign one comment for given blogId
    @PostMapping("/assigncommentforblog/blogid/{id}")
    public String assignCommentForBlog(@PathVariable("id") Long blogId, @RequestBody Comment comment){
        return commentService.assignCommentForBlog(blogId, comment);
    }

    //Post multiple comments for given blog
    @PostMapping("/addmultiplecommentsforblog/blogid/{id}")
    public String addMultipleCommentsForGivenBlog(@PathVariable("id") Long blogId, @RequestBody List<Comment> comments){
        return commentService.addMultipleCommentsForGivenBlog(blogId, comments);
    }



    /* Put Mappings */

    @PutMapping("/updatecomment/commentid/{id}")
    public String updateComment(@PathVariable("id") Long id, @RequestBody Comment comment){
        return commentService.updateComment(id, comment);
    }



    /* Delete Mappings */

    @DeleteMapping("/deletecomment/commentid/{id}")
    public String deleteComment(@PathVariable("id") Long id){
        return commentService.deleteComment(id);
    }
}
