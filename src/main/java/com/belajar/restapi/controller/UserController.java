package com.belajar.restapi.controller;


import com.belajar.restapi.dto.ResponseData;
import com.belajar.restapi.models.entities.User;
import com.belajar.restapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //create new user
    @PostMapping
    public ResponseEntity<ResponseData<User>> create(@Valid @RequestBody User user, Errors errors) {
        ResponseData<User> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(userService.save(user)); // belum valid, karena masih kemungkinan error di service
        return ResponseEntity.ok(responseData);
    }


    //update
    @PutMapping
    public User update (@RequestBody User user) {
        return userService.save(user);
    }

    // select all
    @GetMapping
    public Iterable<User> findAll () {
        return userService.findALl();
    }

    // select one
    @GetMapping("/{id}")
    public User findOne (@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

    // delete
    @DeleteMapping("/{id}")
    public void removeByID (@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
