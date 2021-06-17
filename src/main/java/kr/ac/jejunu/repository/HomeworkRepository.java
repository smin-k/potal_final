package kr.ac.jejunu.repository;

import kr.ac.jejunu.controller.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}