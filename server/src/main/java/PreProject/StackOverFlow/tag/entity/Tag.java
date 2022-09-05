package PreProject.StackOverFlow.tag.entity;

import PreProject.StackOverFlow.basetime.BaseTimeEntity;
import PreProject.StackOverFlow.question.entity.Question_Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String contents;

    @OneToMany(mappedBy = "tag")
    private List<Question_Tag> question_tags = new ArrayList<>();

}