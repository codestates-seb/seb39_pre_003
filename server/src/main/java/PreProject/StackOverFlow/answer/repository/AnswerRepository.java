package PreProject.StackOverFlow.answer.repository;

import PreProject.StackOverFlow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
