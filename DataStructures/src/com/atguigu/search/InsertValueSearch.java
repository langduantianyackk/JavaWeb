package com.atguigu.search;

import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: InsertValueSearch
 * @description: TODO
 * @date 2021/12/26 11:46
 **/
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i=0; i< arr.length;i++){
            arr[i] = i + 1;
        }

//        System.out.println(Arrays.toString(arr));
        int resIndex = insertValueSearch(arr, 0, arr.length - 1, 78);
        System.out.println("resIndex="+resIndex);
    }

    //插值查找算法
    //说明：插值查找算法，也要求数组是有序的
    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找的值
     * @return 如果找到，返回对应下标，没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr,int left, int right, int findVal){
        System.out.println("插值查找次数");
        //注意：findVal < arr[0] 和 findVal > arr[arr.length-1]必须需要，
        //否则得到的mid可能越界
        if(left > right || findVal < arr[0] || findVal > arr[arr.length-1]){
            return -1;
        }
        //求出mid，自适应写法
        int mid = left + (right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        //定义mid的值
        int midVal = arr[mid];

        if(findVal > midVal){//向右递归查找
            return insertValueSearch(arr,mid + 1, right, findVal);
        }else if(findVal < mid){//向左递归查找
            return insertValueSearch(arr,left, mid-1, findVal);
        }else{
         return mid;
        }
    }
}
