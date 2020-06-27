package com.daiwf.javalearndemos.threadpooltest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo
{
    /**
     * 新的处理方式
     */
    @Test
    public void newHandle() throws InterruptedException {
        /**
         * 开启了一个线程池：线程个数是10个
         */
        ExecutorService threadPool =
                Executors.newFixedThreadPool(10);

        /**
         * 使用循环来模拟许多用户请求的场景
         */
        for (int request = 1; request <= 100; request++) {
            threadPool.execute(() -> {
                System.out.println("文档处理开始！");

                try {
                    // 将Word转换为PDF格式：处理时长很长的耗时过程
                    Thread.sleep(1000L * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("文档处理结束！"+Thread.currentThread().getName());
            });
        }

        Thread.sleep(1000L * 1000);
    }

    /**
     * 老的处理方式
     */
    @Test
    public void oldHandle() throws InterruptedException {
        /**
         * 使用循环来模拟许多用户请求的场景
         */
        for (int request = 1; request <= 100; request++) {
            new Thread(() -> {
                System.out.println("文档处理开始！");

                try {
                    // 处理时长很长的耗时过程
                    Thread.sleep(1000L * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("文档处理结束！"+Thread.currentThread().getName());
            }).start();
        }


        Thread.sleep(1000L * 1000);
    }
}
