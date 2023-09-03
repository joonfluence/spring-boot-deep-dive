package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.exception.NoMemberException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
@SpringBootTest
public class MemberRepositoryV1Test {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void crud() throws SQLException, InterruptedException {
        log.info("start");

        //save
        Member member = new Member("memberV0", 10000);
        memberRepository.save(member);

        //findById
        Member memberById = memberRepository.findById(member.getId());
        assertThat(memberById).isNotNull();

        //update: money: 10000 -> 20000
        memberRepository.updateById(20000, member.getId());
        Member updatedMember = memberRepository.findById(member.getId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        //delete
        memberRepository.deleteById(member.getId());
        assertThatThrownBy(() -> memberRepository.findById(member.getId())).isInstanceOf(NoMemberException.class);
    }
}
