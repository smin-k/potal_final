package kr.ac.jejunu.service;

import kr.ac.jejunu.controller.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<Post> findPostList(Pageable pageable);
    Post findPostById(Long id);
}


