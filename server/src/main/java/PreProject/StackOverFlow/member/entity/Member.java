package PreProject.StackOverFlow.member.entity;

import PreProject.StackOverFlow.answer.entity.Answer;
import PreProject.StackOverFlow.basetime.BaseTimeEntity;
import PreProject.StackOverFlow.question.entity.Question;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "member_id")
    private long memberId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String location;

//    @Column(nullable = false)
//    private LocalDateTime active_at;    // modified_at
//
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime join_when;    // created_at

    // --> BaseTimeEntity로 대체(날짜 데이터 자동 저장)

    @Column
    private String profile_image;

    @Column
    private String job;

    @Column(length = 500)
    private String about;

    // Question : Member 테이블 간의 N:1 연관관계 매핑을 위한 JPA 일대다 설정(양방향)
    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    // Answer : Member 테이블 간의 N:1 연관관계 매핑을 위한 JPA 일대다 설정(양방향)
    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

}