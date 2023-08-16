package spring.jpa.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.jpa.domain.member.Member;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @DisplayName("1. 이름이 겹치지 않는 회원에 대해서 회원가입이 정상적으로 처리된다. ")
    @Test
    void test_1(){
        // given
        Member member = Member.builder().name("memberA").build();
        // when
        Long joinedMemberId = memberService.join(member);
        Member foundedMember = memberService.findOne(joinedMemberId);
        // then
        assertEquals(foundedMember.getName(), member.getName());
    }

    @DisplayName("2. 이름이 겹치는 회원에 대해서 에러가 발생된다.")
    @Test
    void test_2(){
        // given
        Member member1 = Member.builder().name("memberA").build();
        Member member2 = Member.builder().name("memberA").build();
        // when
        Long joinedMemberId1 = memberService.join(member1);
        // then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }
}