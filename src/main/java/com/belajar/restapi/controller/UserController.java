package com.belajar.restapi.controller;


import com.belajar.restapi.models.entities.User;
import com.belajar.restapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //create new user
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
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
