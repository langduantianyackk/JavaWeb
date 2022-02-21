package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: MergeSort
 * @description: TODO
 * @date 2021/12/21 15:59
 **/
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};//8个数据合并7次

        int[] arr = new int[8000000];//80000个数据，选择排序为4秒，比冒泡排序15秒快了11秒，所以选择排序速度快于冒泡排序
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间："+format1);

        int[] temp = new int[arr.length];//归并排序需要额外的数组
        mergeSort(arr,0,arr.length-1,temp);
//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+format2);
    }

    //归并排序
    //分 + 合的方法(老师做法)
    private static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid = (left + right)/2;//计算中间索引
            //以下代码核心思想：将1变成多，在将多变成1
            //先向左向右递归分解，当不能分解的时候，也就是分解到最后一层即left=right时，
            // 由于不满足条件不会再分解，开始往下执行代码，开始合并最后不能分解的元素
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid + 1,right,temp);
            //合并:第一次合并的时候，就是把上面不能分解的2个元素进行合并，依次合并左边--> 右边---> 最后左右一起合并
            merge(arr,left,mid,right,temp);
        }
    }

    //合并的方法
    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边的索引
     * @param temp  做中转的数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化的i，左边有序序列的初始索引
        int j = mid + 1;//初始化的j，右边有序序列的初始索引
        int t = 0;//做中转的数组temp的初始索引

        //1,把左右有序数组中的数据按照规则填充到temp中，直到左右两边有序序列有一边处理完毕为止
        while (i <= mid && j <= right) {//
            if (arr[i] <= arr[j]) {//如果左边的有序序列的数据小于等于右边有序序列的数据
                temp[t++] = arr[i];//将左边数据拷贝到temp中
                i++;
            } else {//如果左边的有序序列的数据大于右边有序序列的数据
                temp[t++] = arr[j];//将右边数据拷贝到temp中
                j++;
            }
        }
        //2,把左右有序数组中有剩余的数据依次填充到temp中
        //左边有剩余数据
        while (i <= mid) {
            //将剩余有左边的数据依次放入temp
            temp[t++] = arr[i];
            i++;
        }
        //右边有剩余数据
        while (j <= right) {
            //将剩余有右边的数据依次放入temp
            temp[t++] = arr[j];
            j++;
        }

        //3,把temp拷贝到arr数组中
        //注意，每次并不是拷贝所有数据
        t = 0;
        int tempLeft = left;//表示当前要合并的arr数组的初始索引
        //第一次合并 tempLeft = 0，right=1,//tempLeft = 2，right=3,//tempLeft = 0，right=3,
        //最后一次tempLeft = 0，right=7,
        //将temp数组中的元素依次填充到arr对应的下标中，temp每次的长度为当前合并的所有元素(第一次2，，第二次2)
        for (int k = tempLeft; k <= right; k++) {
           arr[k] = temp[t];
            t++;
        }
        //老师写法
//        while (tempLeft <= right){
//            arr[tempLeft] = temp[t];
//            t++;
//            tempLeft++;
//        }
    }

    //    //分 + 合的方法(我的做法好像不太对)
//    private static void mergeSort(int[] arr,int left,int right,int[] temp){
//        //归并排序推导过程
//        int l = 0;
//        int r = 0;
//        int mid = 0;
////        //第一轮合并
////        for (int i = 0; i < arr.length; i+=2) {
////            l = i;
////            r = i+1;
////            mid = r+l/2;
////            merge(arr,left,mid,right,temp);
////        }
////        //第二轮合并
////        for (int i = 0; i < arr.length; i+=4) {
////            l = i;
////            r = i+3;
////            mid = r+l/2;
////            merge(arr,left,mid,right,temp);
////        }
////        //第三轮合并
////        for (int i = 0; i < arr.length; i+=8) {
////            l = i;
////            r = i+7;
////            mid = r+l/2;
////            merge(arr,left,mid,right,temp);
////        }
//
//        //综上，将三轮和并写成循环
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < arr.length; j+=2*Math.pow(2,i)) {
//                l = j;
//                r = j+(int)Math.pow(2,i+1)-1;
//                mid = (r+j)/2;
//                merge(arr,left,mid,right,temp);
//            }
//        }
//    }
}
