package PreProject.StackOverFlow.answer.entity;

import PreProject.StackOverFlow.basetime.BaseTimeEntity;
import PreProject.StackOverFlow.member.entity.Member;
import PreProject.StackOverFlow.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "answer_id")
    private Long answerId;

    @Column(nullable = false)
    private String  title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int vote;

    // Answer : Question 테이블 간의 N:1 연관관계 매핑을 위한 JPA 다대일 설정
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    // Answer : Member 테이블 간의 N:1 연관관계 매핑을 위한 JPA 다대일 설정
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addQuestion(Question question) {
        this.question = question;
    }

    public void addMember(Member member) {
        this.member = member;
    }
}
