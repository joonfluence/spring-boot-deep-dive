package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.exception.NoMemberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.UUID;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("멤버 객체가 H2 DB에 정상적으로 저장되어야 한다.")
    @Test
    void save() throws SQLException {
        String memberId = UUID.randomUUID().toString().substring(0, 10);
        Member savedMember = memberRepository.save(Member.builder().id(memberId).money(1000).build());
        Assertions.assertEquals(memberId, savedMember.getId());
    }

    @DisplayName("멤버 객체가 정상적으로 조회되어야 한다.")
    @Test
    void findById() throws SQLException {
        String memberId = UUID.randomUUID().toString().substring(0, 10);;
        memberRepository.save(Member.builder().id(memberId).money(1000).build());
        Member member = memberRepository.findById(memberId);
        Assertions.assertEquals(member.getId(), memberId);
    }

    @DisplayName("멤버 객체가 정상적으로 수정되어야 한다.")
    @Test
    void updateById() throws SQLException {
        String memberId = UUID.randomUUID().toString().substring(0, 10);;
        memberRepository.save(Member.builder().id(memberId).money(2000).build());
        memberRepository.updateById(10000, memberId);
        Member member = memberRepository.findById(memberId);
        Assertions.assertEquals(10000, member.getMoney());
    }

    @DisplayName("멤버 객체가 정상적으로 삭제되어야 한다.")
    @Test
    void deleteById() throws SQLException {
        String memberId = UUID.randomUUID().toString().substring(0, 10);;
        memberRepository.save(Member.builder().id(memberId).money(1000).build());
        Member member = memberRepository.findById(memberId);
        Assertions.assertEquals(memberId, member.getId());
        memberRepository.deleteById(memberId);
        Assertions.assertThrows(NoMemberException.class, () -> memberRepository.findById(memberId));
    }
}