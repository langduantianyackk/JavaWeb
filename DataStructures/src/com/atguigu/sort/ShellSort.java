package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: ShellSort
 * @description: TODO
 * @date 2021/12/19 15:03
 **/
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        int[] arr = new int[8000000];//80000个数据，选择排序为4秒，比冒泡排序15秒快了11秒，所以选择排序速度快于冒泡排序
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间："+format1);
//        System.out.println("数组在希尔排序前：");
//        System.out.println(Arrays.toString(arr));
//        shellSort(arr);
//        System.out.println("数组在希尔排序后：");
//        System.out.println(Arrays.toString(arr));
//        shellSort1(arr);//交换式
        shellSort2(arr);//移位式
        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间："+format2);
//        System.out.println(Arrays.toString(arr));
    }

    //我的方式
    //希尔排序
    private static void shellSort(int[] arr){
        //1,计算分组组长
        //2,分组
        //3,对每个分组的所有数字进行直接插入
        //4,重复1,2，3步骤
        //5,直到计算分组组长直到为1时即下一次计算的组长结果为0时结束循环

        //使用逐步推导的方式实现希尔排序
  /*      //第一次分分组
        int len = arr.length/2;//len =5 数组长度为10，分5组每组2个元素
        int temp = 0;

        //对第一次分组的第一组数字进行直接插入即[8,3]
        int insertVal = 0;
        int insertIndex = 0;
        for (int j = 0+len; j < arr.length; j+=len) {
            insertVal = arr[j];
            insertIndex = j - len;
            while (insertIndex>=0 && insertVal < arr[insertIndex]){
                arr[insertIndex + len] = arr[insertIndex];
                insertIndex -= len;
            }
            if(insertIndex + len != j){
                arr[insertIndex+len] = insertVal;
            }
        }
        System.out.println("第一次分组的第一组数字使用直接插入后的数组");
        System.out.println(Arrays.toString(arr));

        //对第一次分组的第二组数字进行直接插入即[9,5]
        for (int j = 1+len; j < arr.length; j+=len) {
            insertVal = arr[j];
            insertIndex = j - len;
            while (insertIndex>=0 && insertVal < arr[insertIndex]){
                arr[insertIndex + len] = arr[insertIndex];
                insertIndex -= len;
            }
            if(insertIndex + len != j){
                arr[insertIndex+len] = insertVal;
            }
        }

        System.out.println("第一次分组的第二组数字使用直接插入后的数组");
        System.out.println(Arrays.toString(arr));*/

 /*       //综上，将第一次分组的所有数字使用for循环每次直接插入
        // 第一次分分组
        int len = arr.length;
        len = len/2;//len =5 数组长度为10，分5组每组2个元素
        int temp = 0;
        //外层循环控制，第一次分组之后，保证所有的组都遍历到了
        //arr.length-len保证了所有分组的第一个元素都遍历到了
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+len; j < arr.length; j+=len) {
                insertVal = arr[j];
                insertIndex = j - len;
                while (insertIndex>=0 && insertVal < arr[insertIndex]){
                    arr[insertIndex + len] = arr[insertIndex];
                    insertIndex -= len;
                }
                if(insertIndex + len != j){
                    arr[insertIndex+len] = insertVal;
                }
            }
            System.out.println("第一次分组的第"+(i+1)+"组数字使用直接插入后的数组");
            System.out.println(Arrays.toString(arr));
        }

        // 第二次分分组
        len = len/2;//len =2 数组长度为10，分2组每组5个元素
        temp = 0;
        //外层循环控制，第一次分组之后，保证所有的组都遍历到了
        //arr.length-len保证了所有分组的第一个元素都遍历到了
        insertVal = 0;
        insertIndex = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+len; j < arr.length; j+=len) {
                insertVal = arr[j];
                insertIndex = j - len;
                while (insertIndex>=0 && insertVal < arr[insertIndex]){
                    arr[insertIndex + len] = arr[insertIndex];
                    insertIndex -= len;
                }
                if(insertIndex + len != j){
                    arr[insertIndex+len] = insertVal;
                }
            }
            System.out.println("第二次分组的第"+(i+1)+"组数字使用直接插入后的数组");
            System.out.println(Arrays.toString(arr));
        }

        // 第三次分分组
        len = len/2;//len =1 数组长度为10，分1组每组10个元素
        temp = 0;
        //外层循环控制，第一次分组之后，保证所有的组都遍历到了
        //arr.length-len保证了所有分组的第一个元素都遍历到了
        insertVal = 0;
        insertIndex = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+len; j < arr.length; j+=len) {
                insertVal = arr[j];
                insertIndex = j - len;
                while (insertIndex>=0 && insertVal < arr[insertIndex]){
                    arr[insertIndex + len] = arr[insertIndex];
                    insertIndex -= len;
                }
                if(insertIndex + len != j){
                    arr[insertIndex+len] = insertVal;
                }
            }
            System.out.println("第二次分组的第"+(i+1)+"组数字使用直接插入后的数组");
            System.out.println(Arrays.toString(arr));
        }*/


        //我的方式进行希尔排序
        //将三次分组结合起来，通过循环遍历
        int insertVal = 0;
        int insertIndex = 0;
        int len = arr.length;
        int temp = 0;
        int num = 0;
        while(len!=0){
            len = len/2;//len =1 数组长度为10，分1组每组10个元素
            num ++;
            //外层循环控制，第一次分组之后，保证所有的组都遍历到了
            //arr.length-len保证了所有分组的第一个元素都遍历到了
            for (int i = 0; i < len; i++) {
                for (int j = i+len; j < arr.length; j+=len) {
                    insertVal = arr[j];
                    insertIndex = j - len;
                    while (insertIndex>=0 && insertVal < arr[insertIndex]){
                        arr[insertIndex + len] = arr[insertIndex];
                        insertIndex -= len;
                    }
                    if(insertIndex + len != j){
                        arr[insertIndex+len] = insertVal;
                    }
                }
//                System.out.println("第"+(num)+"次分组的第"+(i+1)+"组数字使用直接插入后的数组");
//                System.out.println(Arrays.toString(arr));
            }
        }
    }

    //希尔排序(老师做法)
    //方式一，希尔排序时， 对有序序列在插入时采用交换法, 并测试排序速度，速度较慢
    private static void shellSort1(int[] arr){
        int temp = 0;
        int count = 0;
        //综合以下希尔排序的轮数，进行循环处理
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中的所有元素(共gap组，每组有arr.length/gap个元素)，步长为gap
                for (int j = i-gap; j >=0 ; j-=gap) {//遍历有序列
                    if(arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }

//            System.out.println("希尔排序第"+(++count)+"轮后的数组：");
//            System.out.println(Arrays.toString(arr));
        }

//        //使用逐步推导的方式来编写希尔排序
//        //希尔排序的第一轮排序
//        //因为是第一轮分组，是将10个数据分成了5组
//        //i=5表示它是分组中直接插入排序的第二个元素(第一个是0,第二个是5)
//        for (int i = 5; i < arr.length; i++) {
//            //遍历各组中的所有元素(共5组，每组有2个元素)，步长为5
//            //j = i-5表示直接插入排序中的有序列表的最后一个与元素(值为：当前待插入元素-步长)
//            //j-=5表示从最后往前遍历有序列表
//            for (int j = i-5; j >=0 ; j-=5) {
//                if(arr[j] > arr[j+5]){
//                    temp = arr[j];
//                    arr[j] = arr[j+5];
//                    arr[j+5] = temp;
//                }
//            }
//        }
//
//        System.out.println("希尔排序第1轮后：");
//        System.out.println(Arrays.toString(arr));
//
//        //希尔排序的第二轮排序
//        //因为是第二轮分组，是将10个数据分成了5/2=2组
//        //i=2表示它是分组中直接插入排序的第二个元素(第一个是0,第二个是2)
//        //i++表示当i为偶数时，对[3,1,0,9,7]进行直接插入排序，当i为奇数时对[5,6,8,4,2]进行直接插入排序,
//        //也就是不管i为多少,此时待插入元素i一定插入到它前面的步长为2的元素中去
//        for (int i = 2; i < arr.length; i++) {
//            //遍历各组中的所有元素(共2组，每组有5个元素)，步长为2
//            //j = i-2表示直接插入排序中的有序列表的最后一个与元素(值为：当前待插入元素-步长)
//            //j-=2表示从最后往前遍历有序列表
//            for (int j = i-2; j >=0 ; j-=2) {//遍历有序列表，插入元素到步长为2的元素中
//                if(arr[j] > arr[j+2]){
//                    temp = arr[j];
//                    arr[j] = arr[j+2];
//                    arr[j+2] = temp;
//                }
//            }
//        }
//
//        System.out.println("希尔排序第2轮后：");
//        System.out.println(Arrays.toString(arr));
//
//        //希尔排序的第三轮排序
//        //因为是第三轮分组，是将10个数据分成了2/2=1组
//        //i=1表示它是分组中直接插入排序的第二个元素(第一个是0,第二个是1)
//        //也就是不管i为多少,此时待插入元素i一定插入到它前面的步长为2的元素中去
//        for (int i = 1; i < arr.length; i++) {
//            //遍历各组中的所有元素(共1组，每组有10个元素)，步长为1
//            //j = i-1表示直接插入排序中的有序列表的最后一个与元素(值为：当前待插入元素-步长)
//            //j--2表示从最后往前遍历有序列表
//            for (int j = i-1; j >=0 ; j--) {//遍历有序列表，插入元素到步长为1的元素中
//                if(arr[j] > arr[j+1]){
//                    temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//                }
//            }
//        }
//
//        System.out.println("希尔排序第3轮后：");
//        System.out.println(Arrays.toString(arr));
    }

    //希尔排序(老师做法)
    //方式二，希尔排序时， 对有序序列在插入时采用移动法, 并测试排序速度
    private static void shellSort2(int[] arr){
        //通过测试希尔排序时间，发现排序时间变长了，所以优化代码，对交换式的希尔排序 ---> 移步法的希尔排序

        int temp = 0;
        int count = 0;
        int insertVal = 0;//表示待插入元素
        int insertIndex = 0;//表示待插入索引
        //综合以下希尔排序的轮数，进行循环处理
        //使用增量gap，并逐步缩小增量
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            //从第gap个元素开始，逐个对其所在的元素进行直接插入
            for (int i = gap; i < arr.length; i++) {
                insertVal = arr[i];
                insertIndex = i-gap;
                while (insertIndex >=0 && insertVal < arr[insertIndex]){
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }

                //当循环结束之后，此时待插入位置为 insertIndex + gap
                if(insertIndex + gap != i){
                    arr[insertIndex + gap] = insertVal;
                }
            }

//            System.out.println("希尔排序第"+(++count)+"轮后的数组：");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
