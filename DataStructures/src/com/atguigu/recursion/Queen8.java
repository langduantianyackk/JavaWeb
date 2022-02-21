package com.atguigu.recursion;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: Queen8
 * @description: TODO
 * @date 2021/12/16 10:19
 **/
public class Queen8 {
    //定义一个max表示皇后的数量
    int max  = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    //8皇后总共有多少种放法
    private static int count = 0;
    //统计judge判断你多少次
    private static int judgeCount = 0;
    public static void main(String[] args) {
        //测试8皇后是否正确
        Queen8 queen8 = new Queen8();

        queen8.check(0);
        System.out.printf("8皇后问题总共有%d种放法\n",count);
        System.out.printf("8皇后问题一共判断冲突的次数为%d次\n",judgeCount);

    }

    //定义一个方法，放置第n(n初始值为0)个皇后
    private void check(int n){
        if(n==8){//n==8,表示前面的8个皇后位置已经放好了，此时放置的是第九个皇后
            print();
            return;
        }

        //8个皇后没有放置好，依次放入8个皇后
        //循环表示当行固定时判断放入的皇后的列是否冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;//放入皇后的列的位置从0开始递增，每次放入第i列
            //判断第n个皇后放入之后是否冲突
            if(judge(n)){
                check(n+1);//不冲突，放置下一个皇后，及开始递归
            }

            //如果冲突，那么将列array[n] = i的值往后移一位，上面i++就是往后移一位
        }
    }

    //当放置第n(n从0开始)个皇后之后,检测该皇后和前面的n-1个皇后是否冲突
    private boolean judge(int n){
        judgeCount++;
        //依次遍历第i+1行的列和斜线是否和第n个皇后冲突
        for(int i= 0; i< n; i++){
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){//判断冲突的条件
                return false;
            }
        }
        return true;
    }

    //定义方法，将皇后的摆放的位置输出
    public void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
}
