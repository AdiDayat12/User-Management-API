package com.belajar.restapi.repos;


import com.belajar.restapi.models.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepo extends CrudRepository<Profile, Long> {
}
