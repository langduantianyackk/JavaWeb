package com.atguigu.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: HorseChessboard
 * @description: TODO
 * @date 2022/2/6 16:21
 **/
public class HorseChessboard {
    private static int X;//表示棋盘的列数
    private static int Y;//表示棋盘的行数
    //创建数组表示棋盘上的各个位置是否被访问
    private static boolean[] visited;
    //使用属性，标记是否棋盘上的所有位置被访问
    private static boolean finished;//如果为true,表示棋盘上所有位置都被访问，为false，表示棋盘上不是所有位置都被访问
    public static void main(String[] args) {
        //测试骑士周游问题
        X = 6;
        Y = 6;
        //二维数组表示棋盘
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X*Y];//初始值都为false
        int row = 3;//马儿初始位置位置的行，从1开始编号
        int column = 6;//马儿初始位置位置的列，从1开始编号

        //测试耗时
        long start = System.currentTimeMillis();
        //骑士周游算法
        traversalChessBoard(chessBoard, row-1, column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("骑士周游耗时："+ (end-start) + "毫秒");

        //输出棋盘
        for (int[] columns:chessBoard){
            for(int step:columns){
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 功能:，骑士周游问题算法
     * @param chessBoard  棋盘
     * @param row  马儿当前位置的行，从0开始
     * @param column 马儿当前开始位置的列，从0开始
     * @param step 记录马儿走的第几步，初始位置为第1步
     */
    public static void traversalChessBoard(int[][] chessBoard, int row, int column, int step){
        //1,在棋盘上将当前位置标记已经访问
        chessBoard[row][column] = step;//棋盘上起始位置的值为step数
        visited[row*X + column]  = true;//标记当前位置已被访问，将二维数组中的位置转换成一维数组中的位置来表示
        //2,计算当前位置能够访问那些位置
        ArrayList<Point> ps = next(new Point(column, row));
        //6,优化，计算ps的所有point能够访问的下一步的所有集合的数目，进行非递减排序
        sort(ps);
        //3,遍历ps，判断那些位置可以走通
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//每次取得一个point
            //4，判断这个点是否被访问
            if(!visited[p.y*X + p.x]){
                //递归访问下一个能够访问的点
                traversalChessBoard(chessBoard, p.y, p.x, step +1);
            }
        }
        //5,判断马儿是否走完棋盘，使用step和应该走的步数比较，
        // 如果没有达到步数，表示没有走完棋盘，从row和column开始讲整个棋盘清零
        //此处的step < X*Y:有两种情况：1，还没走完，有下一个访问点，正在访问下一个点。2，还没走完，找不到下一个访问点，此路不通
        if(step < X*Y && !finished){
            chessBoard[row][column] = 0;
            visited[row*X + column] = false;
        }else {
            finished = true;
        }
    }

    //ps中的所有point的下一步的所有集合数目，进行非递减排序
    public static void sort(ArrayList<Point> ps){
        //对ps进行排序
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //计算o1的能够访问的下一步的所有位置数目
                int count1 = next(o1).size();
                //计算o2的能够访问的下一步的所有位置
                int count2 = next(o2).size();
                //比较count1和count2

//                //方式一：常用
//                if(count1 < count2){
//                    return -1;
//                }else if(count1 == count2){
//                    return 0;
//                }else {
//                    return 1;
//                }

                //方式一：常用
                return Integer.compare(count1,count2);
            }
        });
    }

    /**
     * 功能：根据当前的棋盘上的点计算出下次能够访问的所有点的集合
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        //创建ArrayList<Point>集合
        ArrayList<Point> ps = new ArrayList<>();
        //创建Point的临时对象
        Point p1 = new Point();
        //计算第5个位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >=0){
            ps.add(new Point(p1));
        }
        //计算第6个位置
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y -2) >=0){
            ps.add(new Point(p1));
        }
        //计算第7个位置
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y -2) >=0){
            ps.add(new Point(p1));
        }
        //计算第0个位置
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y -1) >=0){//<= X:不能等于的原因，它是索引应该小于列X
            ps.add(new Point(p1));
        }
        //计算第1个位置
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y +1) <Y){
            ps.add(new Point(p1));
        }
        //计算第2个位置
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y +2) <Y){
            ps.add(new Point(p1));
        }
        //计算第3个位置
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y +2) <Y){
            ps.add(new Point(p1));
        }
        //计算第4个位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y +1) <Y){
            ps.add(new Point(p1));
        }
        return ps;
    }
}
