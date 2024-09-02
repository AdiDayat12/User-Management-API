package com.belajar.restapi.controller;

import com.belajar.restapi.dto.CommentData;
import com.belajar.restapi.dto.CommentViewDto;
import com.belajar.restapi.dto.ResponseData;
import com.belajar.restapi.models.entities.Comment;
import com.belajar.restapi.models.entities.User;
import com.belajar.restapi.services.CommentService;
import com.belajar.restapi.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Comment>> create (@Valid @RequestBody CommentData commentData, Errors errors) {
        ResponseData<Comment> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError o : errors.getAllErrors()){
                responseData.getMessages().add(o.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        User user = userService.findOne(commentData.getUserID());

        if (user == null) {
            responseData.getMessages().add("User not found");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        Comment comment = modelMapper.map(commentData, Comment.class);
        comment.setId(null); // To ensure that this is a new entity
        comment.setUserFK(user);
        Comment savedComment = commentService.save(comment);
        responseData.setPayload(savedComment);
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<List<CommentViewDto>> findAll () {
        Iterable<Comment> comments = commentService.findAll();
        List<CommentViewDto> commentViewDtos = new ArrayList<>();
        for (Comment c : comments) {
            commentViewDtos.add(new CommentViewDto(
                    c.getUserFK(),
                    c.getText(),
                    c.getCommentDateCreation(),
                    c.getCommentDateUpdated()
            ));
        }
        return ResponseEntity.ok(commentViewDtos);
    }
}
