package spring.jpa.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("1. 멤버 엔티티 생성 테스트")
    @Test
    void test_1(){
        // given
        Member member = Member.builder().username("memberA").build();
        // when
        Long savedId = memberRepository.save(member);
        Member foundMember = memberRepository.find(savedId);
        // then
        assertEquals(foundMember.getId(), member.getId());
        assertEquals(member.getUsername(), foundMember.getUsername());
    }
}