package com.belajar.restapi.controller;

import com.belajar.restapi.dto.ProfileData;
import com.belajar.restapi.dto.ProfileViewDto;
import com.belajar.restapi.dto.ResponseData;
import com.belajar.restapi.models.entities.Profile;
import com.belajar.restapi.models.entities.User;
import com.belajar.restapi.services.ProfileService;
import com.belajar.restapi.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseData<Profile>> create (@Valid @RequestBody ProfileData profileData, Errors errors) {
        ResponseData<Profile> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError o : errors.getAllErrors()) {
                responseData.getMessages().add(o.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        User user = userService.findOne(profileData.getUserID());

        if (user == null) {
            responseData.getMessages().add("User not found");
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }


        Profile profile = modelMapper.map(profileData, Profile.class);
        profile.setUserFK(user);
        Profile savedProfile = profileService.save(profile);

        responseData.setPayload(savedProfile);
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<List<ProfileViewDto>> findAll () {
        Iterable<Profile> profileList = profileService.findAll();
        List<ProfileViewDto> profileViewDtoList = new ArrayList<>();
        for (Profile p : profileList) {
            profileViewDtoList.add(modelMapper.map(p, ProfileViewDto.class));
        }
        return ResponseEntity.ok(profileViewDtoList);
    }
}