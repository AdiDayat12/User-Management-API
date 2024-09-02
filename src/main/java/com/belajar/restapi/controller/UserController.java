package com.belajar.restapi.controller;


import com.belajar.restapi.dto.ResponseData;
import com.belajar.restapi.dto.UserData;
import com.belajar.restapi.models.entities.User;
import com.belajar.restapi.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    //create new user
    @PostMapping
    public ResponseEntity<ResponseData<User>> create(@Valid @RequestBody UserData userData, Errors errors) {
        ResponseData<User> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        User user = modelMapper.map(userData, User.class);
        responseData.setStatus(true);
        responseData.getMessages().add("Created successfully");
        responseData.setPayload(userService.save(user));
        return ResponseEntity.ok(responseData);
    }


    //update
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<User>> update (@PathVariable("id") Long id, @Valid @RequestBody User user, Errors errors) {
        ResponseData<User> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setPayload(null);
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Optional<User> existingUser = Optional.ofNullable(userService.findOne(id));
        if (existingUser.isEmpty()) {
            responseData.getMessages().add("User Not Found");
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        user.setId(id);
        responseData.setStatus(true);
        responseData.getMessages().add("Updated successfully");
        responseData.setPayload(userService.save(user));
        return ResponseEntity.ok(responseData);
    }

    // select all
    @GetMapping
    public Iterable<User> findAll () {
        return userService.findAll();
    }

    // select one
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<User>> findOne (@PathVariable("id") Long id) {
        ResponseData<User> userResponseData = new ResponseData<>();
        User existingUser = userService.findOne(id);
        if (existingUser == null) {
            userResponseData.setStatus(false);
            userResponseData.getMessages().add("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponseData);
        }
        userResponseData.setStatus(true);
        userResponseData.setPayload(existingUser);
        return ResponseEntity.ok(userResponseData);
    }

    // delete
    @DeleteMapping("/{id}")
    public void removeByID (@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
