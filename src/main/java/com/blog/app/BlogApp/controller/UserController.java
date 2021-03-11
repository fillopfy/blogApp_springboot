package com.blog.app.BlogApp.controller;

import com.blog.app.BlogApp.entity.Blog;
import com.blog.app.BlogApp.entity.User;
import com.blog.app.BlogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    /* Get Mappings */

    @GetMapping("/getusers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    //get all blogs with user id as id in pathvariabale
    @GetMapping("/getblogsforuser/userid/{id}")
    public List<Blog> getAllBlogForParticularUser(@PathVariable("id") Long userId){
        return userService.getAllBlogForParticularUser(userId);
    }

    //Get user by profile id
    @GetMapping("/getuserforprofile/profileid/{id}")
    public User getUserForGivenProfile(@PathVariable("id") Long profileId){
        return userService.getUserForGivenProfile(profileId);
    }



    /* Post Mappings */

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    //add user for profile id
    @PostMapping("/adduserforprofile/profileid/{id}")
    public String addUserForGivenProfile(@PathVariable("id") Long prfileId, @RequestBody User user){
        return userService.addUserForGivenProfile(prfileId, user);
    }


    /* Put Mappings */

    @PutMapping("/updateuser/userid/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }


    /* Delete Mappings */

    @DeleteMapping("/deleteuser/userid/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }
}
