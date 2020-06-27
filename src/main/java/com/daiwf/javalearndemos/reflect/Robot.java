package com.daiwf.javalearndemos.reflect;

public class Robot
{
    private String name;
    public void sayHi( String hellosentence){
        System.out.println(hellosentence+" "+name);
    }

    private String throwHello(String tag){
        return "Hello "+tag;
    }

}
