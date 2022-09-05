package PreProject.StackOverFlow.question.repository;


import PreProject.StackOverFlow.question.entity.Question;
import PreProject.StackOverFlow.question.entity.Question_Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
