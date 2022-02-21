package com.atguigu.recursion;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: Migong
 * @description: TODO
 * @date 2021/12/15 17:00
 **/
public class Migong {
    public static void main(String[] args) {
        //先创建二维数组模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板：在将map[3][1] map[3][2]置为1
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        //输出地图
        System.out.println("地图的情况：");
        for(int i = 0; i< 8;i++){
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
//        setWay(map,1,1);
        setWay2(map,1,1);
        //输出新地图，小球走过的路径
        System.out.println("小球走过的路径：");
        for(int i = 0; i< 8;i++){
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
    }

    //使用递归回溯给小球找路
    //1,map表示地图
    //2,i,j表示开始位置的横纵坐标,比如(1,1)
    //3,如果小球能到map[6][5]位置，则通路找到
    //4,约定：当map[i][j]为0时，表示该点还没有走过，当map[i][j]为1时，表示是墙,
    // 当map[i][j]为2时，表示通路可以走,当map[i][j]为3时，表示该点已经走过，但是走不通
    //5,在走迷宫时，定一个策略(方法)下->右->上->左，如果该点走不通在回溯
    /**
     *
     * @param map 表示地图
     * @param i 表示开始位置的横向坐标
     * @param j 表示开始位置的纵向向坐标
     * @return 如果找到通路，返回true，否则返回false
     */
    public static boolean setWay(int[][] map,int i, int j){
        if(map[6][5] == 2){//找到通路
            return true;
        }else{
            if(map[i][j] == 0){
                //按照策略(方法)下->右->上->左
                map[i][j] = 2;//假定该点可以走通
                if(setWay(map,i+1,j)){//向下走
                    return true;
                }else if(setWay(map,i,j+1)){//向右走
                    return true;
                }else if(setWay(map,i-1,j)){//向上走
                    return true;
                }else if(setWay(map,i,j-1)){//向左走
                    return true;
                }else {
                    //3表示该点已经走过，但是走不通
                    map[i][j] = 3;//一个点上下左右都走不通那么该点就走不通
                    return false;
                }
            }else {//如果map[i][j] != 0 那么它的只可能是 1 ，2（表示能走通,走过了） ，3
                return false;
            }
        }
    }

    //修改找路策略：上->右->下->左
    public static boolean setWay2(int[][] map,int i, int j){
        if(map[6][5] == 2){//找到通路
            return true;
        }else{
            if(map[i][j] == 0){
                //按照策略(方法)上->右->下->左
                map[i][j] = 2;//假定该点可以走通
                if(setWay2(map,i-1,j)){//向上走
                    return true;
                }else if(setWay2(map,i,j+1)){//向右走
                    return true;
                } else if(setWay2(map,i+1,j)){//向下走
                    return true;
                }else if(setWay2(map,i,j-1)){//向左走
                    return true;
                }else {
                    //3表示该点已经走过，但是走不通
                    map[i][j] = 3;//一个点上下左右都走不通那么该点就走不通
                    return false;
                }
            }else {//如果map[i][j] != 0 那么它的只可能是 1 ，2（表示能走通,走过了） ，3
                return false;
            }
        }
    }
}
