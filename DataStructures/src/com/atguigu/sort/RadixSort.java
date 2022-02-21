package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: RadixSort
 * @description: TODO
 * @date 2021/12/22 17:17
 **/
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53,3,542,748,14,214};
        int[] arr = new int[80000000];//80000个数据，选择排序为4秒，比冒泡排序15秒快了11秒，所以选择排序速度快于冒泡排序
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间："+format1);
        radixSort(arr);
        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+format2);
    }

    //基数排序
    private static void radixSort(int[] arr){
        //1,得到数组中最大位数的数
        int max = arr[0];
        for (int m = 1; m <arr.length ; m++) {
            if(max<arr[m]){
                max = arr[m];
            }
        }

        //此时的max就是数组中最大的数，那么得到最大的数的位数
        int maxLength = (max + "").length();//方式一，通过拼接空串转化成字符串取其长度
//        int maxLength = String.valueOf(max).length();//方式二，通过拼接空串转化成字符串取其长度


        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际放入了多少个数据，就定义一个一维数组来记录各个桶中每次放入数据的个数
        //例：bucketElementCounts[0]记录的是bucket[0]中存放的数据个数
        int[] bucketElementCounts = new int[10];

        //根据下面的推导可以得到基数排序的代码
        for (int i = 0; i < maxLength; i++) {
            //第i+1轮(针对每个元素的对应的位进行处理)
            //1,将每个元素的对应位数取出，然后看这个数应该放在哪个桶(一维数组)中
            for(int j=0; j<arr.length;j++){
                //取出每个元素的对应位数(依次按照个十百千万...)
                int digitOfElement = (int)(arr[j]/Math.pow(10,i))%10;
                //将对应位数对应的数字放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];//将arr中的元素按照对应位数一次放入每个桶中
                bucketElementCounts[digitOfElement] += 1;//每次记录放入各个桶中元素个数
            }

            //2,将放入桶中数据按照桶的顺序依次取出放入到arr中
            int index = 0;//表示arr数组的当前索引
            for (int k = 0; k < bucket.length; k++) {//依次遍历每个桶
                if(bucketElementCounts[k] != 0){//判断桶中有没有数据
                    //有数据,遍历每个桶中的数据，此时桶中的数据个数为bucketElementCounts[i]
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];//数据取出依次放入
                    }
                }
                //将每个桶中数据放入到arr中后，将记录对应桶中的个数置为0即bucketElementCounts[k] = 0，
                //目的是下一次循环时每个桶中的数据个数得重新计数
                bucketElementCounts[k] = 0;
            }

//            System.out.println("第"+(i+1)+"轮后排序处理，arr="+Arrays.toString(arr));
        }



        //基数排序推导过程
 /*       //第一轮(针对每个元素的个位进行处理)
        //1,将每个元素的个位数取出，然后看这个数应该放在哪个桶(一维数组)中
        for(int j=0; j<arr.length;j++){
            //取出每个元素的个位数
            int digitOfElement = arr[j]/1%10;
            //将个位数对应的数字放入到对应的桶中
            bucketElementCounts[digitOfElement] += 1;//每次记录放入各个桶中元素个数
            bucket[digitOfElement][bucketElementCounts[digitOfElement]-1] = arr[j];//将arr中的元素按照个位数一次放入每个桶中
        }

        //2,将放入桶中数据按照桶的顺序依次取出放入到arr中
        int index = 0;//表示arr数组的当前索引
        for (int i = 0; i < bucket.length; i++) {//依次遍历每个桶
            if(bucketElementCounts[i] != 0){//判断桶中有没有数据
                //有数据,遍历每个桶中的数据，此时桶中的数据个数为bucketElementCounts[i]
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = bucket[i][j];//数据取出依次放入
                }
            }
            //将每个桶中数据放入到arr中后，将记录对应桶中的个数置为0即bucketElementCounts[i] = 0，
            //目的是下一次循环时每个桶中的数据个数得重新计数
            bucketElementCounts[i] = 0;
        }

        System.out.println(Arrays.toString(arr));

        //第二轮(针对每个元素的十位(没有的补零)进行处理)
        //1,将每个元素的十位数取出，然后看这个数应该放在哪个桶(一维数组)中
        for(int j=0; j<arr.length;j++){
            //取出每个元素的十位数
            int digitOfElement = arr[j]/10%10;//748/10 = 74%10 = 4取出十位数4
            //将十位数对应的数字放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];//将arr中的元素按照十位数一次放入每个桶中
            bucketElementCounts[digitOfElement] += 1;//每次记录放入各个桶中元素个数
        }

        //2,将放入桶中数据按照桶的顺序依次取出放入到arr中
        index = 0;//表示arr数组的当前索引
        for (int i = 0; i < bucket.length; i++) {//依次遍历每个桶
            if(bucketElementCounts[i] != 0){//判断桶中有没有数据
                //有数据,遍历每个桶中的数据，此时桶中的数据个数为bucketElementCounts[i]
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = bucket[i][j];//数据取出依次放入
                }
            }

            //将每个桶中数据放入到arr中后，将记录对应桶中的个数置为0即bucketElementCounts[i] = 0，
            //目的是下一次循环时每个桶中的数据个数得重新计数
            bucketElementCounts[i] = 0;
        }

        System.out.println(Arrays.toString(arr));

        //第三轮(针对每个元素的百位(没有的补零)进行处理)
        //1,将每个元素的百位数取出，然后看这个数应该放在哪个桶(一维数组)中
        for(int j=0; j<arr.length;j++){
            //取出每个元素的百位数
            int digitOfElement = arr[j]/100%10;//748/100 = 7%10 = 7取出百位数7
            //将百位数对应的数字放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];//将arr中的元素按照百位数一次放入每个桶中
            bucketElementCounts[digitOfElement] += 1;//每次记录放入各个桶中元素个数
        }

        //2,将放入桶中数据按照桶的顺序依次取出放入到arr中
        index = 0;//表示arr数组的当前索引
        for (int i = 0; i < bucket.length; i++) {//依次遍历每个桶
            if(bucketElementCounts[i] != 0){//判断桶中有没有数据
                //有数据,遍历每个桶中的数据，此时桶中的数据个数为bucketElementCounts[i]
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = bucket[i][j];//数据取出依次放入
                }
            }

            //将每个桶中数据放入到arr中后，将记录对应桶中的个数置为0即bucketElementCounts[i] = 0，
            //目的是下一次循环时每个桶中的数据个数得重新计数
            bucketElementCounts[i] = 0;
        }

        System.out.println(Arrays.toString(arr));*/
    }
}
