package com.example.demo.repository.v1;

import com.example.demo.domain.Member;
import com.example.demo.exception.NoMemberException;
import com.example.demo.repository.MemberQueries;
import com.example.demo.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class MemberRepositoryV1 extends ParentMemberRepositoryV1 implements MemberRepository {

    private final DataSource dataSource;

    public MemberRepositoryV1(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }


    @Override
    @Transactional
    public Member save(Member member) throws SQLException {
        String insertQuery = MemberQueries.CreateMember;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, member.getId());
            preparedStatement.setInt(2, member.getMoney());
            preparedStatement.executeUpdate();
            return member;
        } catch (SQLException e){
            log.error("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    public Member findById(String memberId) throws SQLException {
        String selectQuery = MemberQueries.FindMemberById;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, memberId);
            resultSet = preparedStatement.executeQuery();

            log.info("resultSet {} ", resultSet);
            if(resultSet.next()){
                return Member.builder().id(resultSet.getString("id")).money(resultSet.getInt("money")).build();
            } else {
                throw new NoMemberException("member not found memberId= " + memberId);
            }

        } catch (SQLException e){
            log.error("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    @Transactional
    public void updateById(int money, String memberId) throws SQLException {
        String selectQuery = MemberQueries.FindMemberById;
        String updateQuery = MemberQueries.UpdateMemberById;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, memberId);
            resultSet = preparedStatement.executeQuery();

            log.info("resultSet {} ", resultSet);
            if(!resultSet.next()){
                throw new NoMemberException("member not found memberId= " + memberId);
            }

            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, memberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            log.error("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    @Transactional
    public void deleteById(String memberId) throws SQLException {
        String deleteQuery = MemberQueries.DeleteMemberById;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, memberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            log.error("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }
}
