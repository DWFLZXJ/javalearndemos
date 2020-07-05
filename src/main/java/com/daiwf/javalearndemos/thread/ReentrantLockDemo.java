package com.daiwf.javalearndemos.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable
{
private static ReentrantLock rtl = new ReentrantLock(true);
   @Override
   public void run() {

        while(true){
            try {
                rtl.lock();
                System.out.println("current thread:" + Thread.currentThread().getName() + "get lock");
                Thread.sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();
            }finally {
                rtl.unlock();
            }

        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo etld = new ReentrantLockDemo();
        Thread thread1 = new Thread(etld);
        Thread thread2 = new Thread(etld);
        thread1.start();
        thread2.start();

    }
}
