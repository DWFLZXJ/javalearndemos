package com.daiwf.javalearndemos.thread;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class CompletableFutureDemo {


    public static void main(String[] args) throws InterruptedException {

       new CompletableFutureDemo().testRun();

    }

    public void testRun(){
        ExecutorService threadPool =
                Executors.newFixedThreadPool(10);
        //签名任务
        SignThread signThread = new SignThread();
        CompletableFuture f1 =  CompletableFuture.runAsync(signThread,threadPool);
        //调用日志接口任务
        RemoteThread remoteThread = new RemoteThread();
        CompletableFuture f2 = CompletableFuture.runAsync(remoteThread,threadPool);

        CompletableFuture f3 = f1.thenCombine(f2, (t1, t2)->{ return ""; })
                                 .exceptionally(e->0);

        //两个任务完成后的返回接口值任务
        System.out.println(f3.join());

        System.out.println("整体逻辑结束");
    }

    class SignThread implements Runnable {
        @Override
        public void run() {
            System.out.println("开始签名");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("签名结束");
        }
    }

    class RemoteThread implements Runnable{
        @Override
        public void run() {
            System.out.println("开始调用日志接口");
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //模拟异常
            //throw new RuntimeException();
            System.out.println("调用日志接口结束");
        }
    }





}
