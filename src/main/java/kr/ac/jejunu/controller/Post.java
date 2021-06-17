package kr.ac.jejunu.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Post {

    /*
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * - 기본 키가 자동으로 할당되도록 설정하는 어노테이션이다.
     * - 기본 키 할당 전략을 선택할 수 있는데, 키 생성을 데이터베이스에 위임하는 IDENTITY 전략 사용
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @OneToOne
    private UserInfo userInfo;

    @Column
    private String content;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Homework> homeworks;

    @OneToOne(cascade = CascadeType.ALL)
    private File file;

    @Builder
    public Post(Long id, String title, UserInfo userInfo, String content, File file, LocalDateTime createdDate,
                LocalDateTime updatedDate) {
        this.id = id;
        this.title = title;
        this.userInfo = userInfo;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.file = file;
    }

    public Homework isDoHome(String email){
        try {
            for (Homework homework : homeworks) {
                if (homework.getUserInfo().getEmail().equals(email) ) {
                    return homework;
                }
            }
        }
        catch(NullPointerException e) {
        }
        return new Homework();
    }


//    public Homework getUserHome(UserInfo userInfo) {
//        Homework homework;
//
//       return  result;
//    }



}


