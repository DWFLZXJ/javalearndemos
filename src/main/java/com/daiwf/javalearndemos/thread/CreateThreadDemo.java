package com.daiwf.javalearndemos.thread;

/**
 * 多线程的创建，方式一：继承于Thread类
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用start()
 * <p>
 * 例子：遍历100以内的所有的偶数
 *
 * @author daiwf
 * @create 2020/8/16
 */

//1. 创建一个继承于Thread类的子类


public class CreateThreadDemo extends Thread
{
    //重写run方法
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        //3. 创建Thread类的子类的对象
        CreateThreadDemo createThreadDemo = new CreateThreadDemo();
        //4. 通过此对象调用start()
        createThreadDemo.start();

    }

}
