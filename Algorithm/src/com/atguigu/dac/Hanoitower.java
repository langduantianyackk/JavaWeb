package com.atguigu.dac;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: Hanoitower
 * @description: TODO
 * @date 2022/1/16 12:34
 **/
public class Hanoitower {
    public static void main(String[] args) {
        //测试汉诺塔
        hanoiTower(5,'A','B','C');
    }

    //汉诺塔移动方法
    //使用分治算法
    /**
     *
     * @param num 盘子的个数
     * @param a  起始盘子的石柱
     * @param b  辅助盘子的石柱
     * @param c  目标盘子的石柱
     */
    public static void hanoiTower(int num, char a, char b, char c){
        //1,如果起始盘子数量为1，那么将盘子从A->C
        if(num==1){
            System.out.println("第"+num+"个盘子从"+a+"移动到" + c);
        }else {//2,如果起始盘子的数量>=2,那么可以看做是两个盘子，1，将最下面的一个盘子，2，上面的所有盘子
            //2.1 将上面的num-1个盘子移动到柱子b
            hanoiTower(num-1,a,c,b);
            //2.2 将最下面的盘子移动到柱子c
            System.out.println("第"+num+"个盘子从"+a+"移动到" + c);
            //2.3 将柱子b上面的num-1个盘子移动到柱子c
            hanoiTower(num-1,b,a,c);
        }
    }

}
