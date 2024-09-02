package com.belajar.restapi.services;

import com.belajar.restapi.models.entities.User;
import com.belajar.restapi.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    //if data exists in database then the data will be updated, if not then it will be created
    public User save (User user) {
        return userRepo.save(user);
    }

    public Iterable<User> findAll () {
        return userRepo.findAll();
    }

    public User findOne (Long id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.orElse(null);
    }

    public void delete (Long id) {
        userRepo.deleteById(id);
    }
}
