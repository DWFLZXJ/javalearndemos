package com.daiwf.javalearndemos.threadpooltest;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitDemo
{
    @Test public void submittest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(1000L * 10);
            return "返回值";
        });
        String result = future.get();
        System.out.println("返回结果：" + result);

    }

    @Test public void executeTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                Thread.sleep(1000L * 10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行结果");
        });

    }
}
