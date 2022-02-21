package com.atguigu.sort;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: BubbleSort
 * @description: TODO
 * @date 2021/12/16 17:57
 **/
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3,9,-1,10,-2};
//        int[] arr = {3,9,-1,10,20};

        //为了容易理解冒泡排序，一下为冒泡排序的演变过程
//        int temp = 0;
   /*     //第一趟排序，就是将最大的元素排在最后
        for(int j =0; j < arr.length-1-0; j++){
            if(arr[j] > arr[j+1]){//逆序，则进行交换
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("输出第一趟排序后的结果:");
        System.out.println(Arrays.toString(arr));

        //第二趟排序，就是将第二大的元素排在到数第二位
        for(int j =0; j < arr.length-1-1; j++){
            if(arr[j] > arr[j+1]){//逆序，则进行交换
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("输出第二趟排序后的结果:");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是将第三大的元素排在到数第三位
        for(int j =0; j < arr.length-1-2; j++){
            if(arr[j] > arr[j+1]){//逆序，则进行交换
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("输出第三趟排序后的结果:");
        System.out.println(Arrays.toString(arr));

        //第四趟排序，就是将第四大的元素排在到数第四位
        for(int j =0; j < arr.length-1-3; j++){
            if(arr[j] > arr[j+1]){//逆序，则进行交换
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("输出第四趟排序后的结果:");
        System.out.println(Arrays.toString(arr));*/

        //冒泡排序时间复杂度O(n^2)
        //综上：比较次数m = 趟数 - 1，将趟数结合起来:趟数 = i + 1 那么j < arr.length-1-m=arr.length-1-i
        //冒泡排序的算法优化：定义flag，表示相邻元素是否后发生交换。默认为false
//        boolean flag = false;
//        for (int i = 0; i < arr.length-1; i++) {//外层循环控制趟数
//            for(int j =0; j < arr.length-1-i; j++){//内层循环控制比较次数
//                if(arr[j] > arr[j+1]){//逆序，则进行交换
//                    flag =true;
//                    temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//                }
//            }
//            System.out.println("输出第"+(i+1)+"趟排序后的结果:");
//            System.out.println(Arrays.toString(arr));
//            if(!flag){
//                break;
//            }else{
//                flag =false;
//            }
//        }



//        //输出
//        System.out.println("排序前的数组为："+Arrays.toString(arr));
        //测试冒泡排序花费的时间O(n^2)，给80000个数据
        //测试要给80000个随机的数组  //80000个数据，冒泡排序为15秒
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 8000000);//生成一[0,8000000)之间的随机数
        }
        LocalDateTime localDateTime1 = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String format1 = dateTimeFormatter.format(localDateTime1);
        System.out.println("排序前的时间=" + format1);

        //输出
//        System.out.println("排序前的数组为："+Arrays.toString(arr));
        //通过冒泡排序函数进行排序
        bubbleSort(arr);
        //输出
//        System.out.println("排序后的数组为："+Arrays.toString(arr));

        LocalDateTime localDateTime2 = LocalDateTime.now();
        String format2 = dateTimeFormatter.format(localDateTime2);
        System.out.println("排序后的时间=" + format2);//时间差15s
    }

    private static void bubbleSort(int[] arr){
        //冒泡排序时间复杂度O(n^2)
        //综上：比较次数m = 趟数 - 1，将趟数结合起来:趟数 = i + 1 那么j < arr.length-1-m=arr.length-1-i
        //冒泡排序的算法优化：定义flag，表示相邻元素事发后发生交换。默认为false
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < arr.length-1; i++) {//外层循环控制趟数
            for(int j =0; j < arr.length-1-i; j++){//内层循环控制比较次数
                if(arr[j] > arr[j+1]){//逆序，则进行交换
                    flag =true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
//            System.out.println("输出第"+(i+1)+"趟排序后的结果:");
//            System.out.println(Arrays.toString(arr));
            if(!flag){
                break;
            }else{
                flag =false;
            }
        }
    }
}
