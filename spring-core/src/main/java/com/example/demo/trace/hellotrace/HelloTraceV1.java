package com.example.demo.trace.hellotrace;

import com.example.demo.trace.TraceId;
import com.example.demo.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.demo.util.LogUtil.addSpace;

@Slf4j
@Component
public class HelloTraceV1 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";
    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        // 측정 시작 시간
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }
    public void end(TraceStatus status) {
        complete(status, null);
    }
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        // 멈춘 시간 측정 시작
        Long stopTimeMs = System.currentTimeMillis();
        // 총 소요 시간 = 멈춘 시간 - 시작 시간
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
    }
}
