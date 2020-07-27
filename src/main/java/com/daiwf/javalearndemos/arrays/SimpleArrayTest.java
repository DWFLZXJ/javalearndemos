package com.daiwf.javalearndemos.arrays;

import java.util.Scanner;

public class SimpleArrayTest
{
    public static void main(String[] args) {
        //1、使用Scanner,读取学生个数
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生人数：");
        int number = scanner.nextInt();
        //2、创建数组存储学生成绩，动态初始化
        int[] scores = new int[number];
        //3、给数组中的元素赋值
        System.out.println("请输入" + number + "个学生成绩：");
        for (int i = 0; i < scores.length; i++) {
            scores[i] = scanner.nextInt();
        }
        //4、获取数组中元素最大值
        int maxScore = 0;
        for (int i = 0; i < scores.length; i++) {
            if (maxScore < scores[i]) {
                maxScore = scores[i];
            }
        }
        //5、根据每个学生的成绩与最高分差值，获取每个学生的成绩，并输出等级和成绩
        char level;
        for (int i = 0; i < scores.length; i++) {
            if (maxScore - scores[i] <= 10) {
                level = 'A';
            }
            else if (maxScore - scores[i] <= 20) {
                level = 'B';
            }
            else if (maxScore - scores[i] <= 30) {
                level = 'C';
            }
            else {
                level = 'D';
            }
            System.out.println("student"+i+"score is "+scores[i]+",grade is "+level);
        }

    }

}
