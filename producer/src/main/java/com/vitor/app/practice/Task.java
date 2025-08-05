package com.vitor.app.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task implements Runnable{

    @Override
    public void run() {
        log.info("This class {} is runnable! ", Task.class.getSimpleName());
    }
}
