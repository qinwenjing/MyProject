package com.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qwj on 2017/8/14.
 */
public class LogbackDemo {
    private static Logger log = LoggerFactory.getLogger(LogbackDemo.class);
    public static void main(String[] args) {
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");
    }
}
