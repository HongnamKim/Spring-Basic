package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("회원가입 테스트");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    Member member1 = new Member();
    member1.setName("spring");
    memberService.join(member1);

    Member member2 = new Member();
    member2.setName("spring");
    IllegalStateException e =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalStateException.class, () -> memberService.join(member2));

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }
}
