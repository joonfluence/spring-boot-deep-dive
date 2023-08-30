package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {
    public static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
