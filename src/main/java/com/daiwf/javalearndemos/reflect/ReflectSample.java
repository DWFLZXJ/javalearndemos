package com.daiwf.javalearndemos.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectSample
{
    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException, NoSuchFieldException {
        //方式1
        Class rc = Class.forName("com.daiwf.javalearndemos.reflect.Robot");
        Robot r = (Robot) rc.newInstance();
        System.out.println("ClassName is " + rc.getName());
        Method getHello = rc.getDeclaredMethod("throwHello", String.class);
        getHello.setAccessible(true);
        Object str = getHello.invoke(r, "Bob");
        System.out.println("getHello result is " + str);
        //方式2只能获取共有的方法。
        Method sayHai=rc.getMethod("sayHi",String.class);
        sayHai.invoke(r,"Welcome");

        //如何获取私有类型的属性
        Field name =rc.getDeclaredField("name");
        name.setAccessible(true);
    name.set(r,"Alice");

    }
}
