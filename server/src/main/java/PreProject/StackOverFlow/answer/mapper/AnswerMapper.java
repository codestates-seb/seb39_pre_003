package PreProject.StackOverFlow.answer.mapper;

import PreProject.StackOverFlow.answer.dto.AnswerDto;
import PreProject.StackOverFlow.answer.entity.Answer;
import PreProject.StackOverFlow.question.dto.QuestionDto;
import PreProject.StackOverFlow.question.entity.Question;
import PreProject.StackOverFlow.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    default public Answer answerPostToAnswer(AnswerDto.Post post) {
        if (post == null) {
            return null;
        } else {
            Answer.AnswerBuilder answer = Answer.builder();
            answer.title(post.getTitle());
            answer.contents(post.getContents());
            return answer.build();
        }
    }
    Answer answerPatchToAnswer(AnswerDto.Patch patch);
    default public AnswerDto.Response answerToResponseDto(Answer answer) {
        if (answer == null) {
            return null;
        } else {
            AnswerDto.Response.ResponseBuilder response = AnswerDto.Response.builder();
            response.answerId(answer.getAnswerId());
            response.questionId(answer.getQuestion().getQuestionId());
            response.memberId(answer.getMember().getMemberId());
            response.title(answer.getTitle());
            response.contents(answer.getContents());
            response.vote(answer.getVote());
            return response.build();
        }
    }
    List<AnswerDto.Response> answersToResponseDtos(List<Answer> answers);
}
