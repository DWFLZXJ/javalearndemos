package com.daiwf.javalearndemos.gmssl;

import java.util.concurrent.*;

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

class ClientThread implements Callable {
    CountDownLatch latch;
    public ClientThread(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public Object call() {
        GMClientverify client = new GMClientverify();
        try {
          return client.ClientTest(latch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}



public class PressureTest {



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int count=0;
        int times=1;
        int threads=1000;
        CountDownLatch latch = new CountDownLatch(times);
        //1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(threads);
        //设置线程池的属性

        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        long startTime=System.currentTimeMillis();
        for (int i=0;i<times;i++){
            //service.execute();//适合适用于Runnable
            Future<Object> future =  service.submit(new ClientThread(latch));
            Boolean result=(Boolean)future.get();
            if(!result){
                count++;
            }
        }
        latch.await();
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime)/1000;
        service.shutdown();
        System.out.println("线程数："+threads+";执行"+times+"次请求耗时："+excTime+"s;失败率："+count/times);
    }

}
