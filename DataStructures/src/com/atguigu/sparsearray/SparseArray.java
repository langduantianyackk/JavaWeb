package com.atguigu.sparsearray;

import java.io.*;
import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: SparseArray
 * @description: 稀疏数组和二维数组之间的互转
 * @date 2021/12/6 17:42
 **/
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //二维数组转稀疏数组
        int[][] arr = new int[11][11];
        arr[1][2] = 1;//1代表黑子
        arr[2][3] = 2;//2代表蓝子
        //1，遍历二维数组，得到有效数据的个数sum
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for(int j=0; j < 11;j++){
                if(arr[i][j] != 0){
                    sum++;
                }
            }
        }
//        System.out.println("原始二维数组的有效数据个数："+sum);
        //2,通过有效数据的个数sum，创建稀疏数组sparsearray int[sum+1][3]
        int[][] sparseArr = new int[sum+1][3];
        //3,将原始二维数组的有效个数赋给稀疏数组
        //给稀疏数组的第一行元素赋值
        sparseArr[0][0] = 11;//11为原始数组的行
        sparseArr[0][1] = 11;//11为原始数组的列
        sparseArr[0][2] = sum;//有效数据个数

        //给稀疏数组后面的行赋值
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for(int j=0; j < 11;j++){
                if(arr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }

        //输出稀疏数组
        for(int i=0; i< sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //将稀疏数组写入到文件map.data中
        FileOutputStream fos = new FileOutputStream(new File("map.data"));
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");

        for (int[] ints : sparseArr) {
            for (int item : ints) {
                osw.write(item);//map.data:
            }
        }

        osw.close();

        System.out.println("***********************");
        //从文件map.data中读取稀疏数组
        FileInputStream fis = new FileInputStream(new File("map.data"));
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");

//        int[][]  sparseArr1 = new int[][];
//        char[] cbuf = new char[5];
//        int len = 0;
//        while((len = isr.read(cbuf))!= -1){
//            int anInt = Integer.parseInt(new String(cbuf, 0, len));
//            System.out.println(anInt);
//        }
//
//        isr.close();


        //稀疏数组转二位数组
        //1,通过稀疏数组第一行的数据,创建二维数组
        int[][] arr1 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2，将稀疏数组后面行(从第二行开始)的元素赋值给新创建的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            arr1[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //还原之后的二维数组
        for(int[] array:arr1){
            for(int item:array){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
    }
}
