package com.daiwf.javalearndemos.reflect;

public class ClassLoaderChecker
{
    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader("E:\\", "MyClassLoader");
        Class c = myClassLoader.loadClass("wali");
        c.newInstance();
    }

}
