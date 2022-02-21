package com.atguigu.binarysearchnorecursion;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: BinarySearchNoRecursion
 * @description: TODO
 * @date 2022/1/16 9:59
 **/
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        //测试
        int[] arr = {1,3,8,10,11,67,100};
        int searchIndex = binarySearch(arr, 10);
        if(searchIndex!=-1){
            System.out.println("找到了，数组下标=" + searchIndex);
        }else {
            System.out.printf("数组中%d值不存在",10);
        }
    }

    //二分查找的非递归实现
    /**
     *
     * @param arr 待查找的有序数组
     * @param target 需要查找的值
     * @return 如果找到返回下标mid，否则返回-1
     */
    public static int binarySearch(int[] arr, int target){
        int left = 0;//表示数组的开始索引
        int right = arr.length-1;//表示最大索引
        int mid =  0;//表示中间索引

        while (left <= right){
            mid = (left + right)/2;//计算mid
            if(target == arr[mid]){//找到了
                return mid;
            }else if(target < arr[mid]){
                right = mid - 1;//向左查找
            }else{
                left = mid + 1;//向右查找
            }
        }
        return -1;//没找到
    }
}
