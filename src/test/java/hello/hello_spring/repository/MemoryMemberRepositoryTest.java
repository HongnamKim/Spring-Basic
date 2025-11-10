package hello.hello_spring.repository;

import static org.assertj.core.api.Assertions.*;

import hello.hello_spring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  @BeforeEach
  public void setUp() {
    repository.clearStore();
  }

  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  public void save() {
    Member member = new Member();
    member.setName("회원");

    repository.save(member);

    Member findMember = repository.findById(member.getId()).get();

    assertThat(member).isEqualTo(findMember);
  }

  @Test
  public void findById() {
    Member member = new Member();
    member.setName("회원2");

    repository.save(member);

    Member findByNameMember = repository.findByName(member.getName()).get();

    assertThat(findByNameMember.getName()).isEqualTo(member.getName());
    assertThat(findByNameMember.getName()).isNotEqualTo("회원");
  }

  @Test
  public void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);
  }
}
