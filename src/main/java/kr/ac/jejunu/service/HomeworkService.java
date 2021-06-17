package kr.ac.jejunu.service;

import kr.ac.jejunu.controller.Homework;
import kr.ac.jejunu.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository HomeworkRepository;

    public Page<Homework> findHomeworkList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize());
        return HomeworkRepository.findAll(pageable);
    }

    public Homework findHomeworkById(Long id) {
        return HomeworkRepository.findById(id).orElse(new Homework());
    }
}


