package kr.ac.jejunu.repository;

import kr.ac.jejunu.controller.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}