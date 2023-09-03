package com.example.demo.connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class DBConnectionImplTest {

    @DisplayName("1. 정상적으로 DB 연결이 처리되는지 확인한다.")
    @Test
    void getConnection() {
        Connection connection = DBConnectionImpl.getConnectionV0();
        assertThat(connection).isNotNull();
    }
}