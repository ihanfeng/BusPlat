package com.zhiyin.jagent.runner;

public class Sleeping {
     
    public void randomSleep() throws InterruptedException {

        hello();
        System.out.println("agent test start.");
        // randomly sleeps between 500ms and 1200s
        long randomSleepDuration = (long) (500 + Math.random() * 700);
        System.out.printf("Sleeping for %d ms ..\n", randomSleepDuration);
        Thread.sleep(randomSleepDuration);
        System.out.println("agent test end.");

    }

    public void hello(){

    }
}