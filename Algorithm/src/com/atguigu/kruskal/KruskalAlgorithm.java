package com.atguigu.kruskal;

import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: KruskalAlgorithm
 * @description: TODO
 * @date 2022/1/24 16:45
 **/
public class KruskalAlgorithm {
    private int edgeNum;//边的条数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示连个顶点不连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
/*A*/ { 0, 12, INF, INF, INF, 16, 14},
/*B*/ { 12, 0, 10, INF, INF, 7, INF},
/*C*/ { INF, 10, 0, 3, 5, 6, INF},
/*D*/ { INF, INF, 3, 0, 4, INF, INF},
/*E*/ { INF, INF, 5, 4, 0, 2, 8},
/*F*/ { 16, 7, 6, INF, 2, 0, 9},
/*G*/ { 14, INF, INF, INF, 8, 9, 0}};

        //创建对象
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexs,matrix);
        //打印邻接矩阵
        kruskalAlgorithm.print();
        //调用Kruskal
        kruskalAlgorithm.kruskal();
    }

    //构造器
    public KruskalAlgorithm(char[] vertexs, int[][] matrix) {
        //初始化顶点和边的个数
        int vlen = vertexs.length;
//        this.vertexs = vertexs.clone();//clone()只能浅复制，不能深复制，比如只能复制一维数组，不能复制二维数组

        //初始化顶点 采用复制的方式
        this.vertexs = new char[vlen];//顶点数组
        for (int i = 0; i < vlen; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化邻接矩阵  采用复制的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];//邻接矩阵
            }
        }
        //统计边数
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if(this.matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal(){

        //表示存储最小生成树的边数组的索引
        int index = 0;
        //创建数组存储最小生成树的边
        EData[] rets = new EData[edgeNum];
        //创建终点的数组,保存最小生成树中每个顶点的终点下标
        int[] ends = new int[edgeNum];

        //1,获取所有边的数组
        EData[] edges = getEdges();
        //2,将边按权值由小到大排序
        sortEdges(edges);
        //3,遍历edges，将边从小到到加入最小生成树中，加入之前还要判断当前加入的边和已有的最小生成树是否构成回路，
        // 如果不构成，则加入，否则不加人，查找下一条边
        for (int i = 0; i < edges.length; i++) {
            //4,取得当前边对应的两个顶点下标
            int p1 = getPosition(edges[i].start);//开始顶点
            int p2 = getPosition(edges[i].end);//结束顶点
            //5,求得两个顶点对应的终点
            int m = getEnd(ends,p1);//获取p1的终点下标
            int n = getEnd(ends,p2);//获取p2的终点下标
            //6,判断是否构成回路：即终点是否相同
            if(m!=n){//不构成回路
                //7,更新终点数组
                ends[m] = n;
                //8,将边加入rets数组
                rets[index++] = edges[i];
            }
        }

        //统计打印rets
        System.out.println("最小生成树：");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%-12d\t",matrix[i][j]);//20表示占位，不够20补空格
            }
            System.out.println();
        }
    }

    /**
     * 功能：将边按照权值从小到大冒泡排序
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length-1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if(edges[j].weight > edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     *功能：通过顶点获取其对应的下标
     * @param ch 顶点值 如'A','B'
     * @return 如果存在，顶点对应的下标，否则返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能：通过邻接矩阵获取图中的边放入EData[]数组
     * EData[]数组格式：[['A','B',12],['B','C',10],['C','D',3]]
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j]!=INF){
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能：从终点数组ends中获取下标i对应的终点：目的是用来判断两个顶点的终点是否相同
     * @param ends 表示记录了各个顶点对应的终点的数组，ends数组是在构建最小生成树时，将边加入之后动态的获取得到的
     * @param i 顶点对应的下标
     * @return 返回的是顶点i对应的终点的下标
     */
    private int getEnd(int[] ends, int i){
        //获取终点数组的核心代码
        while (ends[i]!=0){//通过循环得到终点：当一条边加入之后，已有的最小生成树的顶点的终点可能会变，需要找打改变之后的终点
            i = ends[i];
        }
        return i;
    }
}

//创建一个类EData,它的对象实例表示一条边
class EData{
    char start;//表示边的一个点
    char end;//表示边的另一个点
    int weight;//表示存储边的权值
    //构造器
    public EData(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString()便于输出边
    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
