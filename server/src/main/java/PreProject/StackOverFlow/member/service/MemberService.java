package PreProject.StackOverFlow.member.service;

import PreProject.StackOverFlow.exception.BusinessLogicException;
import PreProject.StackOverFlow.exception.ExceptionCode;
import PreProject.StackOverFlow.member.entity.Member;
import PreProject.StackOverFlow.member.mapper.MemberMapper;
import PreProject.StackOverFlow.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    public MemberService(MemberMapper memberMapper, MemberRepository memberRepository) {
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
    }

    public Member login_Service(Member member) {

        Member checked = memberRepository.findByEmail(member.getEmail()).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );
        if(!member.getPassword().equals(checked.getPassword())) {
            throw new BusinessLogicException("비밀번호가 일치하지 않습니다.");
        }

        return checked;
    }

    // 기존의 create() 메서드를 변경하였습니다.
    public void join_Service(Member member) {
        verifyExists(member);
        Member savedMember = memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMember_Id());

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getLocation())
                .ifPresent(location -> findMember.setLocation(location));
        Optional.ofNullable(member.getProfile_image())
                .ifPresent(image -> findMember.setProfile_image(image));
        Optional.ofNullable(member.getJob())
                .ifPresent(job -> findMember.setJob(job));
        Optional.ofNullable(member.getAbout())
                .ifPresent(about -> findMember.setAbout(about));

        return memberRepository.save(findMember);
    }

    public Member findMember(long member_Id) {
        return findVerifiedMember(member_Id);
    }

    public Member findVerifiedMember(long member_Id) {
        Optional<Member> optionalMember = memberRepository.findById(member_Id);

        Member member = optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return member;
    }

    private void verifyExists(Member member) {
        Optional<Member> check;
        check = memberRepository.findByEmail(member.getEmail());
        if (check.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
        check = memberRepository.findByName(member.getName());
        if (check.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }

        // 비밀번호가 다른 계정끼리 동일한 경우는 있을 수 있다고 생각하여 제외함.
//        check = memberRepository.findByPassword(member.getPassword());
//        if (check.isPresent()) {
//            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
//        }
    }


}