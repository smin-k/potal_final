package kr.ac.jejunu.repository;

import kr.ac.jejunu.controller.Homework;
import kr.ac.jejunu.controller.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    Page<Homework> findByPost(Post post, Pageable pageable);
}