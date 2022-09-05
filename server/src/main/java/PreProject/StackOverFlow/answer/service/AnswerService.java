package PreProject.StackOverFlow.answer.service;

import PreProject.StackOverFlow.answer.entity.Answer;
import PreProject.StackOverFlow.answer.repository.AnswerRepository;
import PreProject.StackOverFlow.member.repository.MemberRepository;
import PreProject.StackOverFlow.question.entity.Question;
import PreProject.StackOverFlow.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    private final MemberRepository memberRepository;

    public Answer answer_write_Service(Answer answer, Long questionId, Long memberId) {
        Question findedQuestion = questionRepository.findById(questionId).orElseThrow(
                () -> new IllegalArgumentException("해당 질문 글이 존재하지 않습니다.")
        );

        answer.addQuestion(findedQuestion);
        answer.addMember(memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 존재하지 않습니다.")
        ));
        // 질문 게시글 데이터에도 해당 답변글을 포함시켜야 한다.
        findedQuestion.add_Answer(answer);

        return answerRepository.save(answer);
    }

    public void delete_Service(Long answerId, Long requestMemberId) {
        // 삭제할 답글이 존재하는지 확인한다.
        Answer finded_Answer = find_Answer(answerId);

        // 해당 게시글이 존재하는지 확인한다.
        Question finded_Question = questionRepository.findById(finded_Answer.getQuestion().getQuestionId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        // 삭제 요청한 유저가 작성자 본인인지 확인한다.
        if(requestMemberId != finded_Answer.getMember().getMemberId()) {
            throw new IllegalArgumentException("작성자 본인이 아닙니다.");
        }
        // 본인이 맞으면 글을 삭제한다.
        answerRepository.delete(finded_Answer);
    }

    public Answer find_Answer(Long answerId) {
        Answer findAnswer = answerRepository.findById(answerId).orElseThrow(
                () -> new IllegalArgumentException("해당 답글을 찾을 수 없습니다."));
        return findAnswer;
    }
}
