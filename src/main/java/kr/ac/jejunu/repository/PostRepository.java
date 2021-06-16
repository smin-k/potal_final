package kr.ac.jejunu.repository;

import kr.ac.jejunu.controller.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}