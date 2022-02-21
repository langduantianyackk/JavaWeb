package com.atguigu.prim;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: PrimAlgorithm
 * @description: TODO
 * @date 2022/1/22 16:52
 **/
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图的邻接矩阵是否构建完毕
        //表示顶点数据
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //顶点个数
        int verx = data.length;
        //邻接矩阵
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        //创建图对象
        MGraph graph = new MGraph(verx);

        //创建最小生成树对象
        MinTree minTree = new MinTree();
        //调用生成邻接矩阵
        minTree.createGraph(graph, verx, data, weight);
        //显示邻接矩阵
        minTree.show(graph);
        //显示最小生成树
        minTree.prim(graph,0);//权值之和25:不管从哪个顶点开始，权值之和都是一样的
//        minTree.prim(graph,6);//权值之和25
    }
}

//创建最小生成树 ->村庄的图
class MinTree {
    //创建图的邻接矩阵

    /**
     * @param graph  图的对象
     * @param verx   图的顶点个数
     * @param data   图的顶点数据
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verx, char[] data, int[][] weight) {
        //循环初始化图graph
        for (int i = 0; i < verx; i++) {
            graph.data[i] = data[i];//初始化图的顶点
            for (int j = 0; j < verx; j++) {
                graph.weight[i][j] = weight[i][j];//初始化邻接矩阵
            }
        }
    }

    //显示图
    public void show(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写普利姆方法,生成最小生成树
    /**
     *
     * @param graph 表示生成最小生成树的图
     * @param v 表示从图的第几个顶点开始生成'A'->0 'B'->1 ...'G'->6
     */
    public void prim(MGraph graph,int v) {
        //1，创建数组来存储顶点是否被访问过了，刚开始全部为0表示没有被访问，1表示被访问过了
        int[] visited = new int[graph.verx];

        //假设从A开始即v==0，那么A被访问过了
        visited[v] = 1;

        int minWeight = 10000;//表示默认最小权值
        int visIndex = -1;//表示被访问过的索引
        int unVisIndex = -1;//表示未被访问过的索引
        //2,因为有graph.verx个顶点，所以有graph.verx-1条边，获取每条边
        //k=1表示要获取第1条边，直到获取k=graph.verx边结束循环
        for (int k = 1; k < graph.verx; k++) {
            //3,因为要生成最小生成树，所以如何获取权值最小的边
            for (int i = 0; i < graph.verx; i++) {//i表示当前已经访问过的结点
                for (int j = 0; j < graph.verx; j++) {//j表示和i相邻的未被访问结点
                    if(visited[i]==1 && visited[j] == 0 && minWeight > graph.weight[i][j]){
                        minWeight = graph.weight[i][j];
                        visIndex = i;
                        unVisIndex = j;
                    }
                }
            }

            //输出每次得到的边
            System.out.println("边<" + graph.data[visIndex] + "," + graph.data[unVisIndex] + "> 权值:" + minWeight);

            //4,循环结束，找到一条权值minWeight最小的边，并且下标i访问，j未被访问：将j标记为已访问
            visited[unVisIndex] = 1;

            //5,继续寻找下一条权值最小的边，重置minWeight，visIndex，unVisIndex
            minWeight = 10000;
            visIndex = -1;
            unVisIndex = -1;
        }


    }
}

//创建图
class MGraph {
    int verx;//表示存放图的结点的个数
    char[] data;//表示存放结点数据
    int[][] weight;//表示用邻接矩阵存放边

    public MGraph(int verx) {
        this.verx = verx;
        this.data = new char[verx];
        this.weight = new int[verx][verx];
    }
}
