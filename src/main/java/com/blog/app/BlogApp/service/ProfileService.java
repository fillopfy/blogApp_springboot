package com.blog.app.BlogApp.service;

import com.blog.app.BlogApp.entity.Profile;
import com.blog.app.BlogApp.entity.User;
import com.blog.app.BlogApp.repository.ProfileRepository;
import com.blog.app.BlogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UserRepository userRepository;
    public List<Profile> getAllProfiles() {
        try{
            return profileRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String addProfile(Profile profile) {
        try{
            profileRepository.save(profile);
            return "Added";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String updateProfile(Long profileId, Profile profile) {
        try{
            Profile profile1=profileRepository.findById(profileId).get();
            profile1.setProfileId(profile.getProfileId());
            profile1.setFirstName(profile.getFirstName());
            profile1.setLastName(profile.getLastName());
            profile1.setEmail(profile.getEmail());
            profileRepository.save(profile1);
            return "Updated "+profile1.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String delete(Long profileId) {
        try{
            profileRepository.deleteById(profileId);
            return "Deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Transactional
    public Profile getProfileForGivenUser(Long userId) {
        try{
            return userRepository.findById(userId).get().getProfile();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public String addProfileForGivenUser(Long userId, Profile profile) {
        try{
            User user=userRepository.findById(userId).get();
            profile.setUser(user);
            profileRepository.save(profile);
            user.setProfile(profile);
            userRepository.save(user);
            return "Added "+user.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
