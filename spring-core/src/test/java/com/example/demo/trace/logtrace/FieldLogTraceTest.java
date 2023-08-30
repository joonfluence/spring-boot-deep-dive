package com.example.demo.trace.logtrace;

import com.example.demo.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {

    FieldLogTrace trace = new FieldLogTrace();

    @DisplayName("1. Begin & End")
    @Test
    void test_1(){
        // given
        // when
        TraceStatus status = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        // then
        trace.end(status);
        trace.end(status2);
    }

    @DisplayName("2. Begin & Exception")
    @Test
    void test_2(){
        // given
        // when
        TraceStatus status = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello2");
        // then
        trace.exception(status, new IllegalStateException());
        trace.exception(status2, new IllegalStateException());
    }
}