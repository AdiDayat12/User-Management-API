package com.belajar.restapi.repos;

import com.belajar.restapi.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
