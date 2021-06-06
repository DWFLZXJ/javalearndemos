package com.daiwf.javalearndemos.gmssl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的方式四：使用线程池
 *
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大线程数
 *      keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 *
 * @author daiwf
 * @create 2021-05-28 下午 6:30
 */

class ClientThread implements Runnable{

    @Override
    public void run() {
        GMClientverify client = new GMClientverify();
        try {
            client.ClientTest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



public class PressureTest {

    public static void main(String[] args) {
        //1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(1000);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //设置线程池的属性

//        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();


        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        for (int i=0;i<20000;i++){
            service.execute(new ClientThread());//适合适用于Runnable
        }


//        service.submit(Callable callable);//适合使用于Callable
        //3.关闭连接池
        service.shutdown();
    }

}
