package com.atguigu.search;

import java.util.List;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: SeqSearch
 * @description: TODO
 * @date 2021/12/24 15:03
 **/
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,2,8,6,4,5,-1};
        int index = seqSearch(arr,-11);
        if(index==-1){
            System.out.println("要查找的数据在数组中不存在");
        }else{
            System.out.println("查找的数据在数组中的下标为："+ index);
        }
    }

    /**
     * 实现线性查找，找到一个就返回下标
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr,int value){
        //线性查找是逐一比对，发现如果有相同值，则返回下标
        for (int i = 0; i < arr.length; i++) {
            if(value == arr[i]){
                return i;
            }
        }
        return -1;
    }
}
