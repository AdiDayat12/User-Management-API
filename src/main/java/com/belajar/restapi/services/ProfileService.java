package com.belajar.restapi.services;

import com.belajar.restapi.models.entities.Profile;
import com.belajar.restapi.repos.ProfileRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    public Profile save (Profile profile) {
        return profileRepo.save(profile);
    }
}
