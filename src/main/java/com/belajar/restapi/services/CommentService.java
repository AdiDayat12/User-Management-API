package com.belajar.restapi.services;

import com.belajar.restapi.models.entities.Comment;
import com.belajar.restapi.repos.CommentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;

    public Comment save (Comment comment) {
        return commentRepo.save(comment);
    }

    public Iterable<Comment> findAll () {
        return commentRepo.findAll();
    }
}
