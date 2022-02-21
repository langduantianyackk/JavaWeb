package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: SelectSort
 * @description: TODO
 * @date 2021/12/17 10:30
 **/
public class SelectSort {
    public static void main(String[] args) {
//      int[] arr = {3,9,-1,10,-2};
//      int[] arr = {101,34,119,1,-1,90,123};
        int[] arr = new int[80000];//80000个数据，选择排序为4秒，比冒泡排序15秒快了11秒，所以选择排序速度快于冒泡排序
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间："+format1);

//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));
        //选择排序
        selectSort(arr);
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+format2);
    }

    //选择排序
    public static void  selectSort(int[] arr){
        //使用逐步推导的方式理解选择排序
        //第一轮,
        //原始数据：101,34,119,1
        //第一轮：1,34,119,101

//        //第一轮
//        int minIndex = 0;
//        int min = arr[0];
//        for(int j=0 + 1; j<arr.length; j++){
//            if(min > arr[j]){//说明假定的最小值不是最小的
//                min = arr[j];//重置min
//                minIndex = j;//重置minIndex
//            }
//        }
//
//        //将最小值放入arr[0],把arr[0]和arr[minIndex]进行交换
//        if(minIndex!=0){
//            arr[minIndex] = arr[0];
//            arr[0] = min;
//        }
//
//        System.out.println("第一轮后的数组");
//        System.out.println(Arrays.toString(arr));
//
//        //第二轮
//        minIndex = 1;
//        min = arr[1];
//        for(int j=1 + 1; j<arr.length; j++){
//            if(min > arr[j]){//说明假定的最小值不是最小的
//                min = arr[j];//重置min
//                minIndex = j;//重置minIndex
//            }
//        }
//
//        //将最小值放入arr[1],把arr[]和arr[minIndex]进行交换
//        if(minIndex!=1){
//            arr[minIndex] = arr[1];
//            arr[1] = min;
//        }
//
//        System.out.println("第二轮后的数组");
//        System.out.println(Arrays.toString(arr));
//
//        //第三轮
//        minIndex = 2;
//        min = arr[2];
//        for(int j=2 + 1; j<arr.length; j++){
//            if(min > arr[j]){//说明假定的最小值不是最小的
//                min = arr[j];//重置min
//                minIndex = j;//重置minIndex
//            }
//        }
//
//        //将最小值放入arr[1],把arr[]和arr[minIndex]进行交换
//        if(minIndex!=2){
//            arr[minIndex] = arr[2];
//            arr[2] = min;
//        }
//
//        System.out.println("第三轮后的数组");
//        System.out.println(Arrays.toString(arr));

        //选择排序最终版
        //在推导的过程中，发现规律，使用一个循环解决
        //选择排序的时间复杂度为O(n^2)
        for (int i = 0; i < arr.length-1; i++) {//外层控制轮数,轮数为数组大小-1
            int minIndex = i;
            int min = arr[i];
            for(int j=i + 1; j<arr.length; j++){//内层控制循环找出每轮的真实最小值和下标
                if(min > arr[j]){//说明假定的最小值不是最小的
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }

            //将最小值放入arr[i],把arr[i]和arr[minIndex]进行交换
            if(minIndex!=i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

//            System.out.println("第"+(i+1)+"轮后的数组:");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
