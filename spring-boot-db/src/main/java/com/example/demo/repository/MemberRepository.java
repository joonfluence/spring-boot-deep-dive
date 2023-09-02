package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.sql.SQLException;

public interface MemberRepository {
    Member save(Member member) throws SQLException;
    Member findById(String memberId) throws SQLException;
    void updateById(int money, String memberId) throws SQLException;
    void deleteById(String memberId) throws SQLException;
}
