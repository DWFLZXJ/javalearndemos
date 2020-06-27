package com.daiwf.javalearndemos.thread;

public class WaitSleepDemo
{
    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                System.out.println("thread A is waitting to get lock");
                //获取同步锁之后才能执行代码块中的逻辑。
                synchronized (lock){
                    try{
                        System.out.println("Thread A get lock");
                        Thread.sleep(20);
                        System.out.println("Thread A do wait method");
                        lock.wait(1000);
                        System.out.println("Thread A is done");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        //要让上面的线程先执行。这边加个sleep
        try {
            Thread.sleep(10);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;

        new Thread(new Runnable()
        {
            @Override
            public void run() {
                System.out.println("thread B is waitting to get lock");
                //获取同步锁之后才能执行代码块中的逻辑。
                synchronized (lock){
                    try{
                        System.out.println("Thread B get lock");
                        System.out.println("Thread B is sleeping 10ms");
                        Thread.sleep(10);
                        System.out.println("Thread B is done");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
