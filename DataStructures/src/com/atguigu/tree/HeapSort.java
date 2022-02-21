package com.atguigu.tree;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: HeapSort1
 * @description: TODO
 * @date 2022/1/1 10:05
 **/
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println("数组：" + Arrays.toString(arr));
//        int[] arr = new int[8000000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i]  = (int)(Math.random()*8000000);
//        }
//        LocalDateTime localDateTime1 = LocalDateTime.now();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
//        String time1 = dateTimeFormatter.format(localDateTime1);
//        System.out.println(time1);
//        heapSort(arr);
//        LocalDateTime localDateTime2 = LocalDateTime.now();
//        String time2 = dateTimeFormatter.format(localDateTime2);
//        System.out.println(time2);
    }

    //堆排序
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法进行堆排序");
            return;
        }

        int temp = 0;
        //验证调整成堆是否正确
//        adjuestHeap(arr,1,arr.length);
//        System.out.println("第一次调整后的数组："+Arrays.toString(arr));//4,9,8,5,6
//        adjuestHeap(arr,0,arr.length);
//        System.out.println("第二次调整后的数组："+Arrays.toString(arr));//9,6,8,5,4

        //完成最终代码
        //步骤一 构造初始堆。将给定无序序列构造成一个大顶堆（一般升序采用大顶堆，降序采用小顶堆)。
        //从非叶子结点的最后一个节点arr.length/2 - 1依次递减调整
        for(int i=arr.length/2 - 1; i>=0; i--){//
            adjuestHeap(arr,i,arr.length);
        }

        //步骤二 将堆顶元素与末尾元素进行交换，使末尾元素最大。然后继续调整堆，再将堆顶元素与末尾元素交换，
        //得到第二大元素。如此反复进行交换、重建、交换。
        for (int j = arr.length-1; j >=0 ; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            //经过上面所有非叶子结点调整之后，在交换之前所有的父结点的值大于子结点
            //当第一次交换之后，arr[0]的值不符合上述规则，所以，现在循环每次调整是索引0位置上的值，构成大顶堆
            //0表示待调整的非叶子结点的索引为0，j表示待调整元素个数，第一次为arr.length-1，依次递减
            adjuestHeap(arr,0,j);
        }

    }


    //我的实现方式：不太好理解
    //堆排序
    public static void heapSort1(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法进行堆排序");
            return;
        }

        //验证调整成堆是否正确
//        adjuestHeap(arr,1,arr.length);
//        System.out.println("第一次调整后的数组："+Arrays.toString(arr));//4,9,8,5,6
//        adjuestHeap(arr,0,arr.length);
//        System.out.println("第二次调整后的数组："+Arrays.toString(arr));//9,6,8,5,4

        //完成最终代码
        //1，因为按照从小到大排序，所以构建大顶堆
        //如何构建大顶堆？
        int m = 0;//记录调整好的元素个数
        int temp = 0;//辅助变量temp用于交换
        //int i = arr.length-1;表示从根部选出最大值和最后一个元素进行交换
        for (int i = arr.length - 1; i >= 0; i--) {//外层循环控制待调整二叉树的结点个数，个数从arr.length开始，依次减少
            //步骤一 构造初始堆。将给定无序序列构造成一个大顶堆（一般升序采用大顶堆，降序采用小顶堆)。
            //开始调整，for循环控制调整循环次数
            //int j = arr.length /2 - 1;表示非叶子的最后一个节点
            for (int j = arr.length / 2 - 1; j >= 0; j = (j - 1) / 2) {
                if (j == 0) {//j==0会出现死循环，所以j==0调整完后，break
                    adjuestHeap(arr, 0, arr.length - m);
                    break;
                }
                adjuestHeap(arr, j, arr.length - m);
            }

            //for调整结束，m记录说明调整完了一个数据
            m++;
            //步骤二 将堆顶元素与末尾元素进行交换，使末尾元素最大。然后继续调整堆，再将堆顶元素与末尾元素交换，
            //得到第二大元素。如此反复进行交换、重建、交换。
            if (arr.length - m >= 0) {
                temp = arr[arr.length - m];
                arr[arr.length - m] = arr[0];
                arr[0] = temp;
            }
        }
    }

    //将一个数字(二叉树)调整成大顶堆
    /**
     * 功能：将以i对应的非叶子结点作为父结点将父结点i及其子结点构成的子树调整成大顶堆
     * 举例：int[] arr = {4,6,8,5,9}; ==> 以i=1，调用adjuestHeap==>调整后的大顶推为{4,9,8,5,6}
     * ===>以i=0，调用adjuestHeap==>调整后的大顶推为{9,6,8,5,4}
     *
     * @param arr 待调整的数组
     * @param i   表示非叶子结点在数组中的索引
     * @param len 待调整的数组长度
     */
    public static void adjuestHeap(int[] arr, int i, int len) {

        int temp = arr[i];//先取出当前索引的非叶子结点。保存到temp
        //int k = i*2 + 1;表示从当前结点索引i的左子结点索引开始遍历
        //k< len 表示k的索引小于当前待调整的数组长度
        //k = k*2 +1 表示下一次遍历的是当前结点索引k的左子结点
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            //1，比较左右子结点谁大
            if (k + 1 < len && arr[k] < arr[k + 1]) {//说明右子结点大
                k++;//表示左右子结点中最大值arr[k]的索引k
            }

            //2，比较父结点temp和子结点arr[k]谁大
            if (arr[k] > temp) {//子结点> 父结点
                arr[i] = arr[k];//将子结点最大值赋值给父结点
                //因为子结点有可能还有子结点，所以子结点还需要调整
                //调整策略：先不直接交换(将父结点temp赋给子结点)，而是将当前i的值下移到k
                i = k;//下移之后，i的父结点以上一定是大顶堆的格式，
            } else {
                //父结点值此时就是最大值
                break;
            }
        }

        //当for循环结束之后，此时的i的值就是以i为结点的树(局部子树)的最大值，放在了大顶堆的父结点
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
