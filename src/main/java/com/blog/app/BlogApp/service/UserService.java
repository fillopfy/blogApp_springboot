package com.blog.app.BlogApp.service;

import com.blog.app.BlogApp.entity.Blog;
import com.blog.app.BlogApp.entity.Profile;
import com.blog.app.BlogApp.entity.User;
import com.blog.app.BlogApp.repository.BlogRepository;
import com.blog.app.BlogApp.repository.ProfileRepository;
import com.blog.app.BlogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ProfileRepository profileRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        try{
            userRepository.save(user);
            return "Added";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String updateUser(Long id, User user) {
        try{
            Optional<User> originalUser=userRepository.findById(id);
            originalUser.get().setUserId(user.getUserId());
            originalUser.get().setUserEmail(user.getUserEmail());
            originalUser.get().setUserName(user.getUserName());
            userRepository.save(originalUser.get());
            return "Successfully updated.";
        }catch (Exception e){
            return  e.getMessage();
        }
    }

    public String deleteUser(Long id) {
        try{
            userRepository.deleteById(id);
            return "Successfully deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Transactional
    public String assigningUserToBlog(Long userId, Blog blog) {
        try{
            User user=userRepository.findById(userId).get();
            user.addBlog(blog);
            blog.setUser(user);
            userRepository.save(user);
            blogRepository.save(blog);
            return "Successfully added "+blog.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Transactional
    public List<Blog> getAllBlogForParticularUser(Long userId) {
        try{
            return userRepository.findById(userId).get().getBlogs();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public User getUserForGivenProfile(Long profileId) {
        try{
            return profileRepository.findById(profileId).get().getUser();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public String addUserForGivenProfile(Long profileId, User user) {
        try{
            Profile profile=profileRepository.findById(profileId).get();
            user.setProfile(profile);
            userRepository.save(user);
            profile.setUser(user);
            profileRepository.save(profile);
            return "Added "+profile.toString();
        }catch (Exception e){
            return  e.getMessage();
        }
    }
}
