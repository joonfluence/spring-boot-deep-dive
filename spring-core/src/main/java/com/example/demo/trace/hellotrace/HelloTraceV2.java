package com.example.demo.trace.hellotrace;

import com.example.demo.trace.TraceId;
import com.example.demo.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.demo.util.LogUtil.addSpace;

@Slf4j
@Component
public class HelloTraceV2 {
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

    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        TraceId traceId = beforeTraceId.createNextId();
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
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[" + traceId.getId() + "] " + addSpace(COMPLETE_PREFIX,
                    traceId.getLevel()) + status.getMessage() + " time=" + resultTimeMs + "ms");
        } else {
            log.info("[" + traceId.getId() + "] " + addSpace(EX_PREFIX,
                            traceId.getLevel()) + status.getMessage() + " time=" + resultTimeMs + "ms" + " ex=" + e);
        }
    }
}
