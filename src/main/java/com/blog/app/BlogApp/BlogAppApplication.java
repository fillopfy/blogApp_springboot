package com.blog.app.BlogApp;


import com.blog.app.BlogApp.entity.Blog;
import com.blog.app.BlogApp.entity.Comment;
import com.blog.app.BlogApp.service.BlogService;
import com.blog.app.BlogApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

//	@Autowired
//	BlogService blogService;
//	CommentService commentService;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Blog blog = new Blog();
//		Comment comment = new Comment();
//		blog.setBlogTopic("First blog");
//		blog.setBlogDescription("Mza aa rha h");
//		comment.setCommentContent("Comment kiya kro");
//		String s=commentService.assignCommentForBlog(blog.getBlogId(), comment);
//		System.out.println(s);
//	}
}
