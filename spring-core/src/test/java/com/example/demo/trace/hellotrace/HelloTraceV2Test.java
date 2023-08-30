package com.example.demo.trace.hellotrace;

import com.example.demo.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @DisplayName("1. Begin & End")
    @Test
    void test_1(){
        // given
        HelloTraceV2 trace = new HelloTraceV2();
        // when
        TraceStatus status = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status.getTraceId(), "hello2");
        // then
        trace.end(status2);
        trace.end(status);
    }

    @DisplayName("2. Begin & Exception")
    @Test
    void test_2(){
        // given
        HelloTraceV2 trace = new HelloTraceV2();
        // when
        TraceStatus status = trace.begin("hello");
        // then
        trace.exception(status, new IllegalStateException());
    }
}