package kr.ac.jejunu.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long score;
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("homeworks")
    private UserInfo userInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("homeworks")
    private Post post;

    @CreatedDate
    private LocalDateTime dateCreated;

    @OneToOne(cascade = CascadeType.ALL)
    private File file;

    @Builder
    public Homework(Long id,Long score, String content, UserInfo userInfo, Post post, LocalDateTime dateCreated, File file) {
        this.id = id;
        this.score = score;
        this.content = content;
        this.userInfo = userInfo;
        this.post = post;
        this.dateCreated = dateCreated;
        this.file = file;
    }
}

