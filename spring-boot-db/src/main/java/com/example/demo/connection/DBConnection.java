package com.example.demo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBConnection {
    // JDK8: JDBC 하위 클래스에서 오버라이딩 해서 사용할 일 없어, static 메서드로 선언!
    static Connection getConnection(String URL, String USERNAME, String PASSWORD) {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
