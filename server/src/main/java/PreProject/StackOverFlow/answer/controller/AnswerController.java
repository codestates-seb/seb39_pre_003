package PreProject.StackOverFlow.answer.controller;

import PreProject.StackOverFlow.answer.dto.AnswerDto;
import PreProject.StackOverFlow.answer.entity.Answer;
import PreProject.StackOverFlow.answer.mapper.AnswerMapper;
import PreProject.StackOverFlow.answer.service.AnswerService;
import PreProject.StackOverFlow.question.dto.QuestionDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})

@RestController
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;

    private final AnswerMapper answerMapper;

    @ApiOperation(value = "답변 등록", notes = "등록된 답변 데이터 반환", response = AnswerDto.Response.class)
    @PostMapping("/write")
    public ResponseEntity answer_write(@RequestBody AnswerDto.Post answerPost) {
        Answer writed = answerService.answer_write_Service(answerMapper.answerPostToAnswer(answerPost), answerPost.getQuestionId(), answerPost.getMemberId());
        return new ResponseEntity<>(answerMapper.answerToResponseDto(writed) ,HttpStatus.CREATED);
    }

    @ApiOperation(value = "답글 삭제", notes = "1개 답글 삭제")
    @ApiImplicitParam(name = "answerId", value = "답글 식별번호", required = true)
    @DeleteMapping("/{answerId}")
    public ResponseEntity answer_delete(@PathVariable Long answerId,
                                        @ApiParam(value="답변 등록한 회원식별번호", required=true, example="2")
                                        @RequestParam Long memberId) {
        answerService.delete_Service(answerId, memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ApiOperation(value = "답변 조회", notes = "1개 답변 데이터 반환", response = AnswerDto.Response.class)
    @ApiImplicitParam(name = "answerId", value = "답글 식별번호", required = true)
    @GetMapping("{answerId}")
    public ResponseEntity find_answer(@PathVariable Long answerId) {
        Answer finded = answerService.find_Answer(answerId);
        return new ResponseEntity<>(answerMapper.answerToResponseDto(finded), HttpStatus.OK);
    }
}