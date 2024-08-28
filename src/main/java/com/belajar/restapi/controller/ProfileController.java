package com.belajar.restapi.controller;

import com.belajar.restapi.dto.ProfileData;
import com.belajar.restapi.dto.ResponseData;
import com.belajar.restapi.models.entities.Profile;
import com.belajar.restapi.services.ProfileService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ModelMapper modelMapper;

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

        Profile profile = modelMapper.map(profileData, Profile.class);
        Profile savedProfile = profileService.save(profile);

        responseData.setPayload(savedProfile);
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }
}
