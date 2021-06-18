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
    private Long id;
    private String content;
    private UserInfo userInfo;
    private LocalDateTime createdDate;
    private Post post;
    private File file;
    private Long score;

    public Homework toEntity() {
        Homework build = Homework.builder()
                .id(id)
                .content(content)
                .post(post)
                .file(file)
                .score(score)
                .userInfo(userInfo)
                .dateCreated(createdDate)
                .build();
        return build;
    }

    @Builder
    public HomeworkDto(Long id, String content,UserInfo userInfo, File file, Post post,Long score ,LocalDateTime dateCreated) {
        this.id = id;
        this.score=score;
        this.content = content;
        this.userInfo = userInfo;
        this.file = file;
        this.post = post;
        this.createdDate =dateCreated;
    }
}
