package PreProject.StackOverFlow.question.entity;

import PreProject.StackOverFlow.answer.entity.Answer;
import PreProject.StackOverFlow.basetime.BaseTimeEntity;
import PreProject.StackOverFlow.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@DynamicInsert
@Builder
@Getter
@Setter
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "question_id")
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    @Builder.Default
    private int vote = 0;

    @Column(nullable = false)
    @Builder.Default
    private int view = 0;

    private String questionTagNames;

    @ManyToOne()
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // Tag : Question 테이블 간의 N:N 연관관계 매핑을 위한 JPA 일대다 설정
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Question_Tag> question_tags = new ArrayList<>();

    // Answer : Question 테이블 간의 N:1 연관관계 매핑을 위한 JPA 일대다 설정
    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Answer> answers = new ArrayList<>();

    public void setQuestion_tags(List<Question_Tag> tags){
        this. question_tags = tags;
    }

    public void update_Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void addMember(Member member){
        this.member = member;
    }

    public void update_view_cnt() {
        view += 1;
    }

    public void add_Answer(Answer answer) {
        this.answers.add(answer);
    }
}
