package com.blog.app.BlogApp.controller;

import com.blog.app.BlogApp.entity.Profile;
import com.blog.app.BlogApp.entity.User;
import com.blog.app.BlogApp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    ProfileService profileService;


    /* Get Mappings */

    @GetMapping("/getprofiles")
    public List<Profile> getAllProfiles(){
        return profileService.getAllProfiles();
    }
    
    //Get profile for given userid
    @GetMapping("/getprofileforuser/userid/{id}")
    public Profile getProfileForGivenUser(@PathVariable("id") Long userId){
        return profileService.getProfileForGivenUser(userId);
    }



    /* Post Mappings */

    @PostMapping("/addprofile")
    public String addProfile(@RequestBody Profile profile){
        return profileService.addProfile(profile);
    }

    //add profile for user id
    @PostMapping("/addprofileforuser/userid/{id}")
    public String addProfileForGivenUser(@PathVariable("id") Long userId, @RequestBody Profile profile){
        return profileService.addProfileForGivenUser(userId, profile);
    }


    /* Put Mappings */

    @PutMapping("/updateprofile/profileid/{id}")
    public String updateProfile(@PathVariable("id") Long profileId, @RequestBody Profile profile){
        return profileService.updateProfile(profileId, profile);
    }



    /* Delete Mappings */

    @DeleteMapping("/deleteprofile/profileid/{id}")
    public String delete(@PathVariable("id") Long profileId){
        return profileService.delete(profileId);
    }
}
