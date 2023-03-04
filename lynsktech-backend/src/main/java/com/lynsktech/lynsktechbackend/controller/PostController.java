package com.Lynsktech.lynsktechbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.Lynsktech.lynsktechbackend.persistence.PostDAO;
import com.Lynsktech.lynsktechbackend.model.Post;

@RestController
@RequestMapping("posts")
public class PostController {

    private static final Logger LOG = Logger.getLogger(PostController.class.getName());
    private PostDAO postDAO;

    public PostController(PostDAO postDAO){
        this.postDAO = postDAO;
    }
}
