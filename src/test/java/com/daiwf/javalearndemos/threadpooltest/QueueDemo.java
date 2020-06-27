package com.daiwf.javalearndemos.threadpooltest;

import org.junit.Test;
import org.testng.annotations.AfterTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueDemo
{



    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        //基于数组
        ArrayBlockingQueue queue=new ArrayBlockingQueue<Integer>(5);
        for(int i=0;i<20;i++){
            queue.put(i);
            System.out.println("向队列中添加值："+i);
        }

    }

    @Test
    public void testLinckedBlockingQueue() throws InterruptedException {
        //基于链表
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        for(int i=0;i<20;i++){
            queue.put(i);
            System.out.println("向队列中添加值："+i);
        }
    }

    @Test
    public void testSynchronousQueue() throws InterruptedException {
        //同步移交队列
        SynchronousQueue queue= new  SynchronousQueue<Integer>();
        new Thread(()->{
            try {
                queue.put(1);
                System.out.println("插入值"+Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                queue.take();
                System.out.println("删除值"+Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }



}
