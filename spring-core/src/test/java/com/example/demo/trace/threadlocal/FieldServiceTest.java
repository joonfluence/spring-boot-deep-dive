package com.example.demo.trace.threadlocal;

import com.example.demo.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.demo.util.LogUtil.sleep;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @DisplayName("1. 동시성 이슈가 발생된다")
    @Test
    void field(){
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        // sleep(100);
        threadB.start();
        sleep(3000);
        log.info("main exit");
    }
}
