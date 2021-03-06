package com.daiwf.javalearndemos.arrays;

import org.testng.annotations.Test;

public class AlgorithmArray
{
    //数组的反转
    int[] array = new int[] { 1, 2, 3, 4, 5, 6 };

    //数组的反转方法1
    @Test public void ReverseArray1() {
        int tmp;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
        for (int item : array) {
            System.out.println(item);
        }

    }

    //数组反转方法2
    @Test public void ReverseArray2() {
        int tmp;
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        for (int item : array) {
            System.out.println(item);
        }
    }

    //线性查找
    int dest = 2;

    public void SearchArray1() {
        for (int i = 0; i < array.length; i++) {
            if (dest == array[i]) {
                System.out.println("找到位置" + i);
            }
        }

    }

    //二分查找，前提是有序的。否则也没法用。
    public void SearchArray2() {
        int head = 0;
        int end = array.length - 1;
        boolean flag = true;
        while (head <= end) {
            int middle = (head + end) / 2;
            if (dest == array[middle]) {
                System.out.println("找到了位置为" + dest);
                flag = false;
            }
            else if (array[middle] > dest) {
                end = middle - 1;
            }
            else {
                head = middle + 1;
            }
        }
        if (flag) {
            System.out.println("没找到");
        }

    }

    //冒泡排序,主要就是两个两个比如果是升序的话，左边比右边大就调换一下。需要比array3.length-1次
    @Test public void BubbleSort() {
        int[] array3 = new int[] { 100, 32, 45, -12, 78, 1, 3, -10 };
        for (int i = 0; i < array3.length - 1; i++) {
            for (int j = 0; j < array3.length - 1 - i; j++) {
                if (array3[j] > array3[j + 1]) {
                    int tmp = array3[j];
                    array3[j] = array3[j + 1];
                    array3[j + 1] = tmp;
                }
            }

        }
        for (int i = 0; i < array3.length; i++) {
            System.out.println(array3[i]);
        }

    }

}
