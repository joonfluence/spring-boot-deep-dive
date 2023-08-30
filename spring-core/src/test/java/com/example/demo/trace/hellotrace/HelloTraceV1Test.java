package com.example.demo.trace.hellotrace;

import com.example.demo.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {

    @DisplayName("1. Begin & End")
    @Test
    void test_1(){
        // given
        HelloTraceV1 trace = new HelloTraceV1();
        // when
        TraceStatus status = trace.begin("hello");
        // then
        trace.end(status);
    }

    @DisplayName("2. Begin & Exception")
    @Test
    void test_2(){
        // given
        HelloTraceV1 trace = new HelloTraceV1();
        // when
        TraceStatus status = trace.begin("hello");
        // then
        trace.exception(status, new IllegalStateException());
    }
}