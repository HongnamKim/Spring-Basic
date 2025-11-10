package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.*;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {
  MemoryMemberRepository memberRepository;
  MemberService memberService;

  @BeforeEach
  void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void join() {
    Member member = new Member();
    member.setName("spring");

    Long saveId = memberService.join(member);

    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    Member member1 = new Member();
    member1.setName("spring");
    memberService.join(member1);

    Member member2 = new Member();
    member2.setName("spring");
    IllegalStateException e =
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }

  @Test
  void findMembers() {}

  @Test
  void findOne() {}
}
