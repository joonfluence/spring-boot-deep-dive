package com.example.demo.config;

import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class DBConnectionConfigTest {

    @Test
    void memberRepository() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DBConnectionConfig.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);
        Assertions.assertTrue(memberRepository instanceof MemberRepository);
    }
}