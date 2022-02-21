package com.atguigu.dijkstra;

import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: DijkstraAlgorithm
 * @description: TODO
 * @date 2022/1/26 15:26
 **/
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        //顶点
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        //常量
        final int N = 65535;//表示两个顶点不可连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};

        //创建Graph对象
        Graph graph = new Graph(vertex, matrix);
        //显示邻接矩阵
        graph.showGraph();
        //测试迪杰斯特拉
        graph.dijkstra(0);


    }
}

class Graph{
    private char[] vertex;//存放顶点

    private int[][] matrix;//邻接矩阵

    private VisitedVertex visitedVertex;//访问顶点集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph(){
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%-5d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //迪杰斯特拉算法
    /**
     *
     * @param index :表示出发顶点对应下标
     */
    public void dijkstra(int index){
        //初始化访问顶点的集合
        visitedVertex = new VisitedVertex(vertex.length, index);

        //更新index顶点:这是访问的第一个顶点
        update(index);

        //循环查找第二个待访问的顶点下标并且访问第二个顶点,直到找到第7个待访问的顶点，并且访问第7个顶点之后结束
        for (int i = 1; i < vertex.length; i++) {
            //寻找新的index顶点
            index = visitedVertex.updateArr();
            //更新index顶点
            update(index);
        }

        //显示三个数组visited，pre_visited，dis
        visitedVertex.show();

    }

    /**
     * 功能：更新index顶点到周围未被访问顶点的距离及周围未被访问顶点的前驱结点
     * @param index 表示出发顶点经过index顶点到周围未被访问顶点的索引
     */
    private void update(int index){
        int len = 0;//表示出发顶点经过index顶点到周围未被访问顶点的距离
        for (int j = 0; j < matrix[index].length; j++) {//j表示周围顶点的下标
            //计算len
            //visitedVertex.getDis(index)：出发顶点到index顶点距离
            //matrix[index][j]:index顶点到周围顶点的距离
            len = visitedVertex.getDis(index) + matrix[index][j];
            //如果j顶点未被访问过，并且出发顶点经过index顶点到j顶点的距离(len) < 出发顶点到j的距离(长度为dis[j])
            if(!visitedVertex.isVisIndex(j) && len < visitedVertex.getDis(j)){
                //更新int[] dis
                visitedVertex.updateDis(j,len);
                //更新前驱结点
                visitedVertex.updatePre(j,index);
            }
        }
    }
}

//访问顶点的集合
class VisitedVertex{
    //记录各个顶点是否被访问过，1表示被访问过，0表示未访问，会动态更新
    //保存各顶点是否被访问过，初始时，除了出发顶点为1外，其余都是0。
    public int[] visited;
    //每个下标对应的值为前一个顶点的下标，会动态更新,刚开始都是-1
    public int[] pre_visited;
    //动态记录出发顶点到其他所有顶点的最短距离，比如G为出发顶点，就是记录G到其他顶点的最短距离，会动态更新，求得最短距离存放在dis
    //初始时，为出发顶点到各个顶点的距离，并且和出发顶点不相邻的顶点距离为∞,出发顶点到自身的距离为0
    public int[] dis;

    //构造器
    /**
     *
     * @param length:表示顶点个数
     * @param index：表示出发顶点的下标
     */
    public VisitedVertex(int length, int index){
        //初期化
        this.visited = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化visited:出发顶点index已访问
        this.visited[index] = 1;
        //初始化pre_visited：出发顶点index的没有前驱，所以设为为-1
//        this.pre_visited[index] = -1;
        //初始化dis
        //1,先全部赋值为∞
        Arrays.fill(dis,65535);
        //2,出发顶点到自己距离设为0
        this.dis[index] = 0;
    }

    /**
     * 功能：判断index顶点是否被访问过
     * @param index 顶点下标
     * @return 返回true：index对应顶点被访问过，false：index对应顶点没有被访问过
     */
    public boolean isVisIndex(int index){
        return visited[index] == 1;
    }

    /**
     * 功能：更新出发顶点到index对应顶点的最短距离len
     * @param index:出发顶点之外其他顶点下标
     * @param len ：出发顶点到index对应顶点的最短距离
     */
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    /**
     * 功能：更新index顶点的前驱为pre
     * @param index 表示更新顶点的前驱
     * @param pre 表示要更新的顶点下标
     */
    public void updatePre(int index,int pre){
        pre_visited[index] = pre;
    }


    /**
     * 功能：获取出发顶点到index顶点的最短距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    //继续选择新的访问顶点：比如G访问完之后，将A作为新的访问顶点，
    //那么寻找新访问顶点的条件为：1,该顶点未被访问过 2,从出发顶点带该顶点的路径最短,
    // 也就是查找数组int[] dis中未被访问过的顶点的最小值
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < visited.length; i++) {
            if(visited[i]==0 && min > dis[i]){
                min = dis[i];
                index = i;
            }
        }

        //将index顶点更新成已访问
        visited[index] = 1;
        //循环结束，此时的index就是最短距离并且该顶点未被访问
        return index;
    }

    //显示三个数组visited，pre_visited，dis
    public void show(){
        //顶点
        char[] vertex = {'A','B','C','D','E','F','G'};
        for (int i = 0; i < visited.length; i++) {
//            System.out.println("["+visited[i]+", " + pre_visited[i] +", " + dis[i] +"]");
            System.out.println("["+visited[i]+", " +vertex[i]+", "+ vertex[pre_visited[i]] +", " + dis[i] +"]");
        }
    }

}
