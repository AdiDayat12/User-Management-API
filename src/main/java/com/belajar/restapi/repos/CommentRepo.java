package com.belajar.restapi.repos;

import com.belajar.restapi.models.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Long> {

}
