package kr.ac.jejunu.dto;

import kr.ac.jejunu.controller.File;
import kr.ac.jejunu.controller.Homework;
import kr.ac.jejunu.controller.Post;
import kr.ac.jejunu.controller.UserInfo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HomeworkDto {
    private Integer id;
    private String content;
    private UserInfo userInfo;
    private LocalDateTime createdDate;
    private Post post;
    private File file;

    public Homework toEntity() {
        Homework build = Homework.builder()
                .id(id)
                .content(content)
                .post(post)
                .file(file)
                .dateCreated(createdDate)
                .build();
        return build;
    }

    @Builder
    public HomeworkDto(Integer id, String content,UserInfo userInfo, File file, Post post, LocalDateTime dateCreated) {
        this.id = id;
        this.content = content;
        this.userInfo = userInfo;
        this.file = file;
        this.post = post;
        this.createdDate =dateCreated;
    }
}
