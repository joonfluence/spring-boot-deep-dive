package com.example.demo.config;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.v1.MemberRepositoryV1;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.connection.ConnectionInfo.*;

@Configuration
public class DBConnectionConfig {
    @Bean
    public MemberRepository memberRepository(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return new MemberRepositoryV1(dataSource);
    }
}
