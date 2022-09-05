package PreProject.StackOverFlow.question.repository;

import PreProject.StackOverFlow.question.entity.Question_Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionTagRepository extends JpaRepository<Question_Tag, Long> {
}
