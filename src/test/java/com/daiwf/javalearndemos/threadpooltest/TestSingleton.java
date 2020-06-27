package com.daiwf.javalearndemos.threadpooltest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton
{
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i=0;i<2;i++){
            executorService.execute(new Task());
        }

        System.out.println("结束程序");
    }
}
