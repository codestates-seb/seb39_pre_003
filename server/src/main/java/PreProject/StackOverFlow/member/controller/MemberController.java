package PreProject.StackOverFlow.member.controller;

import PreProject.StackOverFlow.exception.BusinessLogicException;
import PreProject.StackOverFlow.member.dto.MemberDto;
import PreProject.StackOverFlow.member.entity.Member;
import PreProject.StackOverFlow.member.mapper.MemberMapper;
import PreProject.StackOverFlow.member.service.MemberService;
import PreProject.StackOverFlow.question.dto.QuestionDto;
import PreProject.StackOverFlow.question.entity.Question;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;


    // login method 변경
    @ApiOperation(value = "로그인", notes = "로그인한 유저의 정보 반환", response = MemberDto.Response.class)
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberDto.Login login){
        Member member = memberService.loginService(login);
        try{
            System.out.println(member.getEmail());
            return new ResponseEntity<>(member, HttpStatus.OK);
        }catch (BusinessLogicException e){
            System.out.println(member.getEmail());
            return new ResponseEntity<>("different", HttpStatus.BAD_REQUEST);
        }
    }
//    @PostMapping("/login")
////     클라이언트에서 email과 password 전송
//    public ResponseEntity login(MemberDto.Post post) {
//
//        try {
////          모든 단계 성공 시 MEMBER 보드의 모든 데이터 전송
//            Member checked = memberService.login_Service(memberMapper.memberPostToMember(post));
//            return new ResponseEntity<>(memberMapper.memberToMemberResponse(checked), HttpStatus.OK);
//        } catch (BusinessLogicException e) {
////          전송 된 email가 있는지 조회, 있으면 다음단계 / 없으면 'different' 전송
////          해당 email의 password와 1의 password가 일치하는지 검사, 일치하면 다음단계 / 없으면 'different' 전송
//            return new ResponseEntity<>("different", HttpStatus.BAD_REQUEST);
//        }
//    }

    @ApiOperation(value = "회원 가입", notes = "요청된 회원 데이터 저장 및 반환", response = String.class)
    @PostMapping("/join")
//      클라이언트에서 email, name, password, image 전송
    // + @RequestBody Annotation 추가
    public ResponseEntity join(@Valid @RequestBody MemberDto.Post memberPost) {
//      전송 된 email가 있는지 조회, 있다면 'diffferent' 전송 / 없으면 다음 단계 진행
        System.out.println(memberPost.getName());
        System.out.println(memberPost.getEmail());
        System.out.println(memberPost.getPassword());
        System.out.println(memberPost.getProfile_image());
        try {
//          전송된 email, name, password, image를 저장 및 'success' 출력
            memberService.join_Service(memberMapper.memberPostToMember(memberPost));
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (BusinessLogicException e) {
            return new ResponseEntity<>("different", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "회원 조회", notes = "1개 회원 데이터 반환", response = MemberDto.Response.class)
    @ApiImplicitParam(name = "memberId", value = "회원 식별번호")
    @GetMapping("/{memberId}")
    public ResponseEntity find(@PathVariable Long memberId) {
        Member findMember = memberService.find_Member(memberId);
        return new ResponseEntity<>(memberMapper.memberToMemberResponse(findMember), HttpStatus.OK);
    }
}