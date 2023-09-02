package com.example.demo.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;

@Slf4j
public class DBConnectionImpl extends ConnectionInfo implements DBConnection {
    public static Connection getConnection() {
        Connection connection = DBConnection.getConnection(URL, USERNAME, PASSWORD);
        log.info("get connection={}, class={}", connection, connection.getClass());
        return connection;
    }
}
