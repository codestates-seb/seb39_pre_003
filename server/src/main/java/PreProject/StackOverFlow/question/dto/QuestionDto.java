package PreProject.StackOverFlow.question.dto;

import PreProject.StackOverFlow.answer.dto.AnswerDto;
import PreProject.StackOverFlow.answer.entity.Answer;
import PreProject.StackOverFlow.member.entity.Member;
import PreProject.StackOverFlow.question.entity.Question_Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.List;

@Getter
public class QuestionDto {

    @ApiModel(value = "QuestionResponse" ,description = "질문 데이터 전송 모델")
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response {
        @ApiModelProperty(notes = "질문 식별 번호", example = "1")
        private Long questionId;
        @ApiModelProperty(notes = "제목", example = "제목1")
        private String title;
        @ApiModelProperty(notes = "내용", example = "내용1")
        private String contents;
        @ApiModelProperty(notes = "질문 투표 수", example = "0")
        private int vote;
        @ApiModelProperty(value = "질문 조회 수", example = "0")
        private int view;
        @ApiModelProperty(value = "질문에 달린 태그이름 String 값", example = "aa bb cc")
        private String questionTagNames;
        @ApiModelProperty(value = "질문에 달린 답글들", example = "배열 값으로 전달됩니다.")
        private List<AnswerDto.Response> answers;

    }

//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Getter
//    public static class PostA {
//        private Long memberId;
//
//        private String title;
//
//        private String contents;
//
//        private String questionTagNames;
//    }

    @ApiModel(value = "QuestionPost" ,description = "질문 등록 모델")
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Post {
        @ApiModelProperty(value = "질문 등록한 회원 식별번호", required = true, example = "2")
        private Long memberId;
        @ApiModelProperty(value = "제목", required = true, example = "제목1")
        private String title;
        @ApiModelProperty(value = "내용", required = true, example = "내용1")
        private String contents;

//        private List<Question_Tag> question_tags;
        @ApiModelProperty(value = "질문에 넣을 태그이름 String 값", required = false ,example = "aa bb cc")
        private String questionTagNames;
    }

    @ApiModel(value = "QuestionPatch" ,description = "질문 글 수송 모델")
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Patch {
        @ApiModelProperty(value = "질문 식별번호", required = false, example = "1")
        private Long questionId;
        @ApiModelProperty(value = "질문 등록한 회원 식별번호", required = false, example = "2")
        private Long memberId;
        @ApiModelProperty(value = "제목", required = false, example = "제목11")
        private String title;
        @ApiModelProperty(value = "제목", required = false, example = "제목11")
        private String contents;
    }
}
