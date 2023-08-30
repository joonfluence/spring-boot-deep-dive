package com.example.demo.trace;

import com.example.demo.trace.logtrace.FieldLogTrace;
import com.example.demo.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}
