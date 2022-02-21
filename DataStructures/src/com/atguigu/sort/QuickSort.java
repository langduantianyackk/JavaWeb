package com.atguigu.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: QuickSort
 * @description: TODO
 * @date 2021/12/20 15:17
 **/
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70,78};
//        int[] arr = {-9,67,24,-99,0,88,79,65,70};

        //创建80000条数据，它的执行时间为0~1秒比选择排序，冒泡排序块
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]  = (int)(Math.random()*8000000);
        }
        LocalDateTime localDateTime1 = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String time1 = dateTimeFormatter.format(localDateTime1);
        System.out.println(time1);

        quickSort(arr,0,arr.length-1);
//        System.out.println("arr="+Arrays.toString(arr));

        LocalDateTime localDateTime2 = LocalDateTime.now();
        String time2 = dateTimeFormatter.format(localDateTime2);
        System.out.println(time2);
    }

    //快速排序
    private  static  void quickSort(int[] arr,int left, int right){
        //快速排序的推导过程
        int l = left;//左索引
        int r = right;//右索引
        int temp = 0;//作为交换的中间变量

        //定义pivot中轴值
        int pivot = arr[(left+right)/2];

        //while循环目的是，将小于pivot的值放在左边
        //将大于pivot的值放在左边
        while(l < r){
            //当pivot左边的值小于pivot值时，一直循环，直到大于等于pivot值时才退出循环
            while(arr[l] < pivot){
                l+=1;
            }

            //当pivot右边的值大于pivot值时，一直循环，直到小于等于pivot值时才退出循环
            while(arr[r] > pivot){
                r-=1;
            }

            //循环结束的条件为l>=r,此时左边的值都小于pivot,右边的值大于pivot
            if(l>=r){
                break;
            }

            //交换
            if(arr[l]!=arr[r]){
                temp  = arr[l];
                arr[l] = arr[r];
                arr[r]  = temp;
            }

            //有可能左边(右边)的值都小于(大于)pivot值时，而右边(左边)的值还有小于(大于)pivot的情况
            //此时将右边(左边)下标左移(有移)一位继续判定
            //如果交换完之后，发现arr[l] == pivot，那么r前移
            if(arr[l] == pivot){
                r-=1;
            }

            //如果交换完之后，发现arr[r] == pivot，那么l后移
            if(arr[r]==pivot){
                l+=1;
            }
        }

        //如果l==r,必须l++，r--，否则栈溢出，没懂？
        if(l==r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }

        //向右递归
        if(right>l){
            quickSort(arr,l,right);
        }
    }

}
