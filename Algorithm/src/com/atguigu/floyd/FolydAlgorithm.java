package com.atguigu.floyd;

import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: FolydAlgorithm
 * @description: TODO
 * @date 2022/2/5 16:05
 **/
public class FolydAlgorithm {
    public static void main(String[] args) {
        //测试
        //顶点数组
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示顶点之间不可连接
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
        //创建Graph对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        //佛洛依德算法得到最短路径
        graph.floyd();
        //显示数组
        graph.show();

    }
}

//创建图
class Graph{
    private char[] vertex;//存放顶点
    private int[][] dis;//距离表(保存从各个顶点出发到其他顶点的距离，动态更新，结果保存该数组中)
    private int[][] pre;//前驱关系表(保存到达各个顶点的前驱顶点)

    /**
     *
     * @param len 表示顶点个数，用来初始化前驱关系表
     * @param matrix 邻接矩阵，用来初始化距离表
     * @param vertex 用来初始化顶点数组
     */
    public Graph(int len, int[][] matrix, char[] vertex){
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[len][len];
        //初始化前驱顶点
        for (int i = 0; i < len; i++) {
            Arrays.fill(pre[i],i);//刚开始未访问时各个顶点出发到其他顶点的前驱为自己
        }
    }

    //显示dis,pre数组
    public void show(){
        //顶点数组
        char[] vertex = {'A','B','C','D','E','F','G'};
        for (int i = 0; i < dis.length; i++) {
            //显示一行pre数组
            System.out.print("前驱顶点：");
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + "\t");
            }
            System.out.println();
            //显示一行dis数组
            for (int j = 0; j < dis.length; j++) {
                System.out.print("("+vertex[i]+"到"+vertex[j]+"的最短路径是"+dis[i][j] + ")\t");
            }
            System.out.println();
            System.out.println();
        }
    }

    //佛洛依德算法
    public void floyd(){
        int len = 0;//计算出发点经过顶点到达顶点的路径
        //第一层循环k取值表示中间顶点的下标
        for (int k = 0; k < vertex.length; k++) {
            //第二层循环i取值表示出发顶点的下标
            for (int i = 0; i < vertex.length; i++) {
                //第三层循环j取值表示终点顶点的下标
                for (int j = 0; j < vertex.length; j++) {
                    //计算len
                    len = dis[i][k] + dis[k][j];
                    //判断经过中间顶点的路径长度 < 直连的路径长度
                    if(len < dis[i][j]){
                        //设置从i出发到达终点j的最终路径为len
                        dis[i][j] = len;
                        //设置前驱
                        pre[i][j] = k;
//                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
