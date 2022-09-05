//package PreProject.StackOverFlow;
//
//import PreProject.StackOverFlow.member.entity.Member;
//import PreProject.StackOverFlow.question.entity.Question;
//import PreProject.StackOverFlow.question.repository.QuestionRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
////@ActiveProfiles("realdb")
//public class AnswerRepositoryTest {
//    @Autowired
//    QuestionRepository questionRepository;
//
//    @Test
//    public void saveTest() {
//        Member member1 = Member.builder()
//                .name("길동이1")
//                .email("abc@abc.com")
//                .password("1234")
//                .build();
//
//        Question question1 = Question.builder()
//                .title("title4")
//                .member(member1)
//                .contents("content4")
//                .build();
//
//        Question saved = questionRepository.save(question1);
//
//        assertThat(saved.getTitle(), is(question1.getTitle()));
//        System.out.println("saved.getVote : " +saved.getVote());
//    }
//}