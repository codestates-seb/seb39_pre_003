package PreProject.StackOverFlow.question.controller;

import PreProject.StackOverFlow.dto.MultiResponseDto;
import PreProject.StackOverFlow.member.service.MemberService;
import PreProject.StackOverFlow.question.dto.QuestionDto;
import PreProject.StackOverFlow.question.entity.Question;
import PreProject.StackOverFlow.question.entity.Question_Tag;
import PreProject.StackOverFlow.question.mapper.QuestionMapper;
import PreProject.StackOverFlow.question.service.QuestionService;
import PreProject.StackOverFlow.tag.repository.TagRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final TagRepository tagRepository;

    // 질문 등록
    @ApiOperation(value = "질문 등록", notes = "등록된 질문 데이터 반환", response = QuestionDto.Response.class)
    @PostMapping("/write")
    public ResponseEntity write(@RequestBody QuestionDto.Post questionPost) {
        // questionPostA에서 String 으로 받은 tag 이름들 split해서 tagRepository를 통해 각각 Tag객체 얻어온 후
        // QuestionDto.Post 객체로 변환하여 QuestionMapper를 통해 Question으로 변환.
        // QuestionMapper에 questionPostToQuestion()메서드 임의로 정의하였음.
        // 현 로직을 수행하려면, Tag들이 미리 저장되있어야하는데 그렇지 못함. 따라서 그냥 Question 테이블에 tags 스트링을 저장하기로 함
        Question writed = questionService.write_Service(mapper.questionPostToQuestion(questionPost),questionPost.getMemberId());


        return new ResponseEntity<>(mapper.questionToQuestionResponse(writed), HttpStatus.CREATED);
//        Question question = new Question();
//        List<Question_Tag> tags = Arrays.stream(questionPost.getQuestionTagNames().split(" "))
//                .map(str -> tagRepository.findByName(str))
//                .map(tag -> new Question_Tag(question, tag))
//                .collect(Collectors.toList());
//        QuestionDto.Post questionPost = new QuestionDto.Post(
//                questionPostA.getMemberId(), questionPostA.getTitle(), questionPostA.getContents(), tags);
//
//        Question writed = questionService.write_Service(mapper.questionPostToQuestion(questionPost));
    }

    @ApiOperation(value = "질문 조회", notes = "1개 질문 데이터 반환", response = QuestionDto.Response.class)
    @ApiImplicitParam(name = "questionId", value = "질문 식별번호", required = true)
    @GetMapping("/{questionId}")
    public ResponseEntity read(@PathVariable Long questionId) {
        Question finded = questionService.read_Service(questionId);
        return new ResponseEntity<>(mapper.questionToQuestionResponse(finded), HttpStatus.OK);
    }

    @ApiOperation(value = "질문 리스트 조회", notes = "페이지 별 질문리스트 반환", response = MultiResponseDto.class)
    @GetMapping("list")
    public ResponseEntity get_list(@ApiParam(value="현재 페이지", required=true, example="1")
                                    @RequestParam("page") int page,
                                    @ApiParam(value="페이지 크기", required=true, example="10")
                                    @RequestParam("size") int size) {
        Page<Question> page_list = questionService.get_list_Service(page,size);
        System.out.println("page_list = " + page_list.getSize());
        List<Question> finded_list = page_list.getContent();
        System.out.println("page_list = " + finded_list.size());

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(finded_list), page_list)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "질문 수정", notes = "질문 데이터 수정", response = String.class)
    @PatchMapping
    public ResponseEntity modify(@RequestBody QuestionDto.Patch questionPatch) {

        questionService.modify_Service(mapper.questionPatchToQuestion(questionPatch));

        return new ResponseEntity<>("수정이 완료되었습니다.", HttpStatus.OK);
    }

    @ApiOperation(value = "질문 삭제", notes = "1개 질문 삭제", response = String.class)
    @ApiImplicitParam(name = "questionId", value = "질문 식별번호", required = true)
    @DeleteMapping("/{questionId}")
    public ResponseEntity delete(@PathVariable Long questionId) {
        questionService.delete_Service(questionId);
        return new ResponseEntity<>("삭제가 완료되었습니다.", HttpStatus.NO_CONTENT);
    }
}
