package com.daiwf.javalearndemos.arrays;
/*
二维数组的使用
1二维数组的申明和初始化
2如何调用数组的指定位置的元素
3如何获取数组的长度
4如何遍历数组
5数组元素的默认初始化值
6数组的内存解析
 */

import javax.sound.midi.SoundbankResource;

public class MultiArrayTest
{
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};//一维数组
        //静态初始化
        int[][] arr1 = new int[][]{{1,2,3},{4,5},{6,7,8}};
        //动态初始化1
        String[][] arr2 = new String[3][2];
        //动态初始化2光写行不写列也是可以的
        String[][] arr3 = new String[3][];
        //下面这种写法也是正确的。
        int[][] arr4 = {{1,2,3},{4,5}};

        //2如何调用数组的指定位置的元素
        System.out.println(arr1[0][1]);//2

        //3如何获取数组的长度

        System.out.println(arr1.length);
        System.out.println(arr1[0].length);





    }

}
