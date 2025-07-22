package com.vitor.app.practice;

public class Task implements Runnable{

    @Override
    public void run() {
        System.out.println("This class is runnable!");
    }
}
