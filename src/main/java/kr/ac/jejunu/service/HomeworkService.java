package kr.ac.jejunu.service;

import kr.ac.jejunu.controller.Homework;
import kr.ac.jejunu.controller.Post;
import kr.ac.jejunu.dto.HomeworkDto;
import kr.ac.jejunu.repository.HomeworkRepository;
import kr.ac.jejunu.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    public Page<Homework> findHomeworkList(Pageable pageable, Long pid) {
        Post post = postService.findPostById(pid);
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());
        return homeworkRepository.findByPost(post,pageable);
    }

    public Homework findHomeworkById(Long id) {
        return homeworkRepository.findById(id).orElse(new Homework());
    }

    public Homework save(HomeworkDto homeworkDto) {
        Post post = homeworkDto.getPost();
        Homework homework = homeworkDto.toEntity();
        List<Homework> homeworks = post.getHomeworks();
        homeworks.add(homework);
        post.setHomeworks(homeworks);
        postRepository.save(post);
        return homeworkRepository.save(homework);
    }

}


