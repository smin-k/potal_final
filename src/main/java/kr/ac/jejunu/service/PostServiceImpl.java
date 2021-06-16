package kr.ac.jejunu.service;

import kr.ac.jejunu.controller.Post;
import kr.ac.jejunu.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Page<Post> findPostList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());
        return postRepository.findAll(pageable);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElse(new Post());
    }
}
