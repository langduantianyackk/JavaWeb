package com.atguigu.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: BinarySearch
 * @description: TODO
 * @date 2021/12/24 15:37
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000,1000,1000,1234};//定义一个有序数组（默认从小到大排序）
        int index = binarySearch(arr, 0, arr.length - 1, 1000);
        if (index == -1) {
            System.out.println("要查找的数据在数组中不存在");
        } else {
            System.out.println("查找的数据在数组中的下标为：" + index);
        }

        System.out.println("************************");
        List<Integer> searchIndexList = binarySearch1(arr, 0, arr.length - 1, 1000);
        System.out.println("searchIndexList="+searchIndexList);
    }

    //二分法查找（前提数组必须有序）
    /**
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 待查找的数
     * @return 如果找到，返回查找值的下标，否则返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        int mid = (left + right) / 2;//计算中间值下标
        int midVal = arr[mid];//中间值
        if (left > right) {//递归完整个数组，仍然没有找到findVal,也需要结束递归，放left > right就退出
            return -1;
        }

        //如果大于中间值，说明要查找的值可能在mid值的右边，因此需要递归向左查找,left = mid + 1
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//如果小于中间值，说明要查找的值可能在mid值的左边，因此需要递归向左查找,right = mid-1
            return binarySearch(arr, left, mid - 1, findVal);
        } else {//如果等于中间值，说明找到了，结束
            return mid;
        }
    }

    /*
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     * 思路分析
     * 1，在找到mid索引值时，先不返回
     * 2，向mid索引值的左边扫描，将所有满足1000的元素下标，加入到集合ArrayList
     * 3，向mid索引值的右边扫描，将所有满足1000的元素下标，加入到集合ArrayList
     * 4，将ArrayList返回即可
     */
    /**
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 待查找的数
     * @return 如果找到，则的list.size()不等于0，否则没找到
     */
    public static List<Integer> binarySearch1(int[] arr, int left, int right, int findVal) {
        int mid = (left + right) / 2;//计算中间值下标
        int midVal = arr[mid];//中间值
        List<Integer> resIndexList = new ArrayList<>();

        if (left > right) {//递归完整个数组，仍然没有找到findVal,也需要结束递归，放left > right就退出
            return resIndexList;
        }

        //如果大于中间值，说明要查找的值可能在mid值的右边，因此需要递归向左查找,left = mid + 1
        if (findVal > midVal) {
            return binarySearch1(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//如果小于中间值，说明要查找的值可能在mid值的左边，因此需要递归向左查找,right = mid-1
            return binarySearch1(arr, left, mid - 1, findVal);
        } else {//如果等于中间值，说明找到了，结束

            //向mid索引值的左边扫描，将所有满足1000的元素下标，加入到集合ArrayList
            int temp = mid -1;
            while (true){
                if(temp<0 || arr[temp]!=findVal){//向左进行扫描，如果扫描的索引temp < 0或者扫描到的值不为findVal，结束扫描
                    break;
                }
                resIndexList.add(temp);
                temp-=1;
            }

            //将mid索引加入到resIndexList中
            resIndexList.add(mid);
            //向mid索引值的右边扫描，将所有满足1000的元素下标，加入到集合ArrayList
            temp = mid + 1;
            while(true){
                if(temp>arr.length-1 || arr[temp]!=findVal){
                    break;
                }
                resIndexList.add(temp);
                temp+=1;
            }
        }
        return resIndexList;
    }
}
