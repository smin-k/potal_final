package kr.ac.jejunu.dto;

import kr.ac.jejunu.controller.File;
import kr.ac.jejunu.controller.Post;
import kr.ac.jejunu.controller.UserInfo;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {
    private Long id;
    private UserInfo userInfo;
    private String title;
    private String content;
    private File file;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Post toEntity() {
        Post build = Post.builder()
                .id(id)
                .title(title)
                .userInfo(userInfo)
                .content(content)
                .file(file)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
        return build;
    }

    @Builder
    public PostDto(Long id, UserInfo userInfo, String title, String content, File file, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.userInfo = userInfo;
        this.title = title;
        this.content = content;
        this.file = file;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
