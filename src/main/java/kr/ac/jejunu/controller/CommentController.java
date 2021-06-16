package kr.ac.jejunu.controller;

import kr.ac.jejunu.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    PostRepository PostRepository;
}
