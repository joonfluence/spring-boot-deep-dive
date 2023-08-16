package spring.jpa.domain;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.jpa.domain.member.Member;
import spring.jpa.domain.member.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    @Autowired
    MemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @DisplayName("1. 멤버 엔티티 생성 테스트")
    @Test
    void test_1(){
        // given
        Member member = Member.builder().name("memberA").build();
        // when
        Long savedId = memberRepository.save(member);
        Member foundMember = memberRepository.findById(savedId);
        // then
        assertEquals(foundMember.getId(), member.getId());
        assertEquals(member.getName(), foundMember.getName());
    }

    @DisplayName("2. 멤버 이름으로 조회하기")
    @Test
    void test_2(){
        // given
        String givenName = "memberA";
        Member member = Member.builder().name(givenName).build();
        // when
        memberRepository.save(member);
        Member foundMember = memberRepository.findByName(givenName).get(0);
        // then
        assertEquals(givenName, foundMember.getName());
    }
}