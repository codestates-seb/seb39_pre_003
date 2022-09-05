package PreProject.StackOverFlow;

import PreProject.StackOverFlow.member.entity.Member;
import PreProject.StackOverFlow.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void findByEmailTest() {
        String email = "abc@abc.com";

        Member saveMember = Member.builder()
                .email(email)
                .name("길동이")
                .password("1234")
                .build();

        memberRepository.save(saveMember);

        Optional<Member> member = memberRepository.findByEmail(email);
        System.out.println(member.isPresent());
    }
}
