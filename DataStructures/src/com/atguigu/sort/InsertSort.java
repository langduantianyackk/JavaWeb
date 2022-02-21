package com.atguigu.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: InsertSort
 * @description: TODO
 * @date 2021/12/17 16:52
 **/
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101,34,119,1};

        //创建80000条数据，它的执行时间为0~1秒比选择排序，冒泡排序块
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]  = (int)(Math.random()*8000000);
        }
        LocalDateTime localDateTime1 = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String time1 = dateTimeFormatter.format(localDateTime1);
        System.out.println(time1);

//        System.out.println("排序之前的数组：");
//        System.out.println(Arrays.toString(arr));
        insertSort(arr);
//        System.out.println("排序之后的数组：");
//        System.out.println(Arrays.toString(arr));

        LocalDateTime localDateTime2 = LocalDateTime.now();
        String time2 = dateTimeFormatter.format(localDateTime1);
        System.out.println(time2);

    }

    //插入排序
    public static void insertSort(int[] arr){

 /*       //演示插入排序的推导过程
        //第一轮插入 {101,34,119,1} => {34,101,119,1}
        //定义待插入的数
        int insertVal = arr[1];
        //即arr[1]前面的一个表示待插入的索引
        int insertIndex = 1-1;

        //给insertVal找到插入的位置
        //1,insertIndex>=0:保证给insertVal找插入位置不越界
        //2,insertVal < arr[insertIndex]:说明此时待插入的数还没有找打插入的位置
        while(insertIndex>=0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];//后移
            insertIndex--;
        }

        //当while循环结束的时候,待插入的位置找到了，insertIndex + 1
        arr[insertIndex+1]  = insertVal;
        System.out.println("第一轮插入之后的数组：");
        System.out.println(Arrays.toString(arr));

        //第二轮插入 [34, 101, 119, 1] => [34, 101, 119, 1]
        //定义待插入的数
        insertVal = arr[2];
        //即arr[1]前面的一个表示待插入的索引
        insertIndex = 2-1;

        //给insertVal找到插入的位置
        //1,insertIndex>=0:保证给insertVal找插入位置不越界
        //2,insertVal < arr[insertIndex]:说明此时待插入的数还没有找打插入的位置
        while(insertIndex>=0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];//后移
            insertIndex--;
        }

        //当while循环结束的时候,待插入的位置找到了，insertIndex + 1
        arr[insertIndex+1]  = insertVal;
        System.out.println("第二轮插入之后的数组：");
        System.out.println(Arrays.toString(arr));

        //第三轮插入 [34, 101, 119, 1] => [1,34, 101, 119]
        //定义待插入的数
        insertVal = arr[3];
        //即arr[1]前面的一个表示待插入的索引
        insertIndex = 3-1;

        //给insertVal找到插入的位置
        //1,insertIndex>=0:保证给insertVal找插入位置不越界
        //2,insertVal < arr[insertIndex]:说明此时待插入的数还没有找打插入的位置
        while(insertIndex>=0 && insertVal < arr[insertIndex]){
            arr[insertIndex+1] = arr[insertIndex];//后移
            insertIndex--;
        }

        //当while循环结束的时候,待插入的位置找到了，insertIndex + 1
        arr[insertIndex+1]  = insertVal;
        System.out.println("第三轮插入之后的数组：");
        System.out.println(Arrays.toString(arr));*/

        //将以上三轮结合起来,for循环表示
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {//外层循环控制轮数
            //定义待插入的数
            insertVal = arr[i];
            //即arr[1]前面的一个表示待插入的索引
            insertIndex = i-1;

            //给insertVal找到插入的位置
            //1,insertIndex>=0:保证给insertVal找插入位置不越界
            //2,insertVal < arr[insertIndex]:说明此时待插入的数还没有找打插入的位置
            while(insertIndex>=0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];//后移
                insertIndex--;
            }

            //判断是否需要赋值即待插入的点不是循环的位置i末尾，就赋值
            if(insertIndex + 1 != i){
                //当while循环结束的时候,待插入的位置找到了，insertIndex + 1
                arr[insertIndex+1]  = insertVal;
            }
//            System.out.println("第"+(i)+"轮插入之后的数组：");
//            System.out.println(Arrays.toString(arr));
        }

    }
}
