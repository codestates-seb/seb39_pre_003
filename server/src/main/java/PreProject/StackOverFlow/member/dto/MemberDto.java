package PreProject.StackOverFlow.member.dto;

import PreProject.StackOverFlow.validator.NotSpace;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class MemberDto {
    @ApiModel(value = "MemberPostDto" ,description = "회원 가입 모델")
    @Getter
    @AllArgsConstructor
    public static class Post{
        @ApiModelProperty(value = "회원 이름", required = true, example = "회원1")
        @NotBlank
        private String name;
        @ApiModelProperty(value = "회원 이메일", required = true, example = "abc@abc.com")
        @NotBlank
        @Email
        private String email;
        @ApiModelProperty(value = "회원 비밀번호", required = true, example = "1234")
        @NotBlank
        private String password;
        @ApiModelProperty(value = "회원 프로필 이미지", required = false, example = "이미지 String 값")
        private String profile_image;
    }

    @ApiModel(value = "MemberPatchDto" ,description = "회원 수정 모델")
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @ApiModelProperty(value = "회원 식별번호", required = true, example = "1")
        private long memberId;
        @ApiModelProperty(value = "회원 이름", required = false, example = "회원1")
        @NotSpace
        private String name;
        @ApiModelProperty(value = "회원 거주지역", required = false, example = "서울")
        private String location;
        @ApiModelProperty(value = "회원 프로필 이미지", required = false, example = "이미지 String 값")
        private String profile_image;
        @ApiModelProperty(value = "회원 직업", required = false, example = "개발자")
        private String job;
        @ApiModelProperty(value = "회원 상세설명(500자)", required = false, example = "저는 개발자이며~ 입니다")
        private String about;

        public void setMemberId(long memberId){
            this.memberId = memberId;
        }
    }

    @ApiModel(value = "MemberResponseDto" ,description = "회원 정보 전송 모델")
    @Getter
    @AllArgsConstructor
    public static class Response{
        @ApiModelProperty(value = "회원 식별번호", required = true, example = "1")
        private Long memberId;
        @ApiModelProperty(value = "회원 이름", example = "회원1")
        private String name;
        @ApiModelProperty(value = "회원 이메일", example = "abc@abc.com")
        private String email;
        @ApiModelProperty(value = "회원 거주지역", example = "서울")
        private String location;
        @ApiModelProperty(value = "회원 수정 일자", example = "2022-09-01T02:04:43")
        private LocalDateTime modifiedDate;
        @ApiModelProperty(value = "회원 가입 일자", example = "2022-09-01T02:04:43")
        private LocalDateTime createdDate;
        @ApiModelProperty(value = "회원 프로필 이미지", example = "이미지 String 값")
        private String profile_imgae;
        @ApiModelProperty(value = "회원 직업", example = "개발자")
        private String job;
        @ApiModelProperty(value = "회원 상세설명(500자)", example = "저는 개발자이며~ 입니다")
        private String about;
    }

    @Getter
    @AllArgsConstructor
    public static class Login{
        @ApiModelProperty(value = "회원 이메일", required = true, example = "abc@abc.com")
        private String email;
        @ApiModelProperty(value = "회원 비밀번호", required = true, example = "1234")
        private String password;
    }
}