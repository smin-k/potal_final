package kr.ac.jejunu.dto;

import kr.ac.jejunu.controller.File;
import kr.ac.jejunu.controller.Post;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private File file;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Post toEntity() {
        Post build = Post.builder()
                .id(id)
                .title(title)
                .author(author)
                .content(content)
                .file(file)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
        return build;
    }

    @Builder
    public PostDto(Long id, String author, String title, String content, File file, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.file = file;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
