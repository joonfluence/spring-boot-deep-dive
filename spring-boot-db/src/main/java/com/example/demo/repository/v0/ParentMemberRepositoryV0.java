package com.example.demo.repository.v0;

import com.example.demo.connection.DBConnectionImpl;
import com.example.demo.repository.MemberQueries;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class ParentMemberRepositoryV0 extends MemberQueries {

    public void close(Connection connection, Statement statement, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e){
                log.info("Error {} ", e);
            }
        }

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e){
                log.info("Error {}", e);
            }
        }


        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e){
                log.info("Error {}", e);
            }
        }
    }

    public Connection getConnection(){
        return DBConnectionImpl.getConnectionV0();
    }
}
