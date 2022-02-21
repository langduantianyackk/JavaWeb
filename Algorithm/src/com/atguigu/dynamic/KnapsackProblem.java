package com.atguigu.dynamic;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: KnapsackProblem
 * @description: TODO
 * @date 2022/1/17 9:00
 **/
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品的重量
        int[] val = {1500,3000,2000};//物品的价值
        int m = 4;//表示背包的容量
        int n = val.length;//物品数量

        //v[i][j] 表示放入第i个物品到容量为j的背包的物品的最大价值
        int[][] v = new int[n+1][m+1];//n+1 ：加1表示物品数量为0情况考虑，m+1：加1表示背包容量为0情况考虑

        //定义二维数组，保存放入背包物品的情况
        int[][] path = new int[n+1][m+1];//因为记录的是放入背包的每种情况，所以大小和v相同
        
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//初始化背包容量为0时的放入物品时的最大价值为0
        }

        for (int j = 0; j < v[0].length; j++) {
            v[0][j] = 0;//初始化放入物品为0个时的背包中物品最大价值为0
        }

        //根据公式动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                //放入第i个物品的容量超过最大容量j
                if(w[i-1] > j){//当前程序i从1开始,但是w[i]取值从0开始，所以i-1
                    v[i][j] = v[i-1][j];//采用上一次的最大价值的策略
                }else {
//                    v[i][j] = Math.max(v[i-1][j], val[i-1] + v[i-1][j-w[i-1]]);
                    if(v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        //输出v，查看放入第i个物品到背包容量为j时物品的最大价值
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }

//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if(path[i][j]==1){//表示放入背包的情况，有好多情况，最后放入的情况才是想要的结果
//                    System.out.printf("第%d个商品放入背包\n",i);
//                }
//            }
//        }

        int i = path.length-1;
        int j = path[0].length -1;
        while (i>0 && j>0){
            if(path[i][j]==1){
                System.out.printf("第%d个商品放入背包\n",i);
                j -= w[i-1];//从path数组后往前找，先找第一个，然后从剩余容量中找第二个...,此时的j表示找到放入物品后剩余的容量
            }
            i--;//
        }


    }
}
