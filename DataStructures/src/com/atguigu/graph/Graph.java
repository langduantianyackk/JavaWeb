package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: Graph
 * @description: TODO
 * @date 2022/1/11 17:01
 **/
public class Graph {
    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的个数
    private boolean[] isVisited;//定义boolean[]数组，记录某个结点是否被访问

    public static void main(String[] args) {
        //实现图的方式
        //邻接矩阵：实现分析：1，使用ArrayList存储顶点(String类型) 2，使用二维数组存储顶点之间的连接 int[][] edges
        //测试
        //顶点的个数
//        int n = 5;
        int n = 8;
        //需要添加的顶点
//        String[] vertexValue = {"A", "B", "C", "D", "E"};
        String[] vertexValue = {"1", "2", "3", "4", "5","6","7","8"};

        //创建图对象
        Graph graph = new Graph(n);

        //添加顶点
        for (String value : vertexValue) {
            graph.insertVertex(value);
        }
        //添加边
//        graph.insertEdge(0, 1, 1);//A-B之间的边
//        graph.insertEdge(0, 2, 1);//A-C之间的边
//        graph.insertEdge(1, 2, 1);//B-C之间的边
//        graph.insertEdge(1, 3, 1);//B-D之间的边
//        graph.insertEdge(1, 4, 1);//B-E之间的边
        //更新边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(5,6,1);


//        //显示矩阵
        System.out.println("显示邻接矩阵：");
        graph.showGraph();
//
//        //显示顶点数
//        System.out.println("显示顶点数:" + graph.getNumOfVertex());
//
//        //显示边数
//        System.out.println("显示边数：" + graph.getNumOfEdges());
//
//        //获取顶点的值
//        System.out.println("顶点值：" + graph.getValueByIndex(3));
//
//        //获取权值
//        System.out.println("权值：" + graph.getWeight(1, 3));

        //深度优先遍历
        System.out.println("深度优先遍历:");
        graph.dfs();//1->2->4->8->5->3->6->7->
        System.out.println();
        //广度优先遍历
        System.out.println("广度优先遍历:");
        graph.bfs();
    }

    //构造器初始化属性
    public Graph(int n) {
        //初始化vertexList和edges
        this.edges = new int[n][n];
        this.vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    //得到第一个邻接节点的下标w
    /**
     * @param index 当前结点v
     * @return 如果存在，返回对应下标，否则返回-1
     */
    public int getFirstNeighboor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    /**
     * @param v1 当前结点下标
     * @param v2 邻接结点下标
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历搜索算法：DFS
    /**
     *
     * @param isVisited 表示存储顶点元素是否被访问的状态
     * @param i 从第i(下标)个元素开始，深度优先遍历   i= 0,1,2....
     */
    private void dfs(boolean[] isVisited, int i) {
        //输出当前顶点
        System.out.print(getValueByIndex(i) + "->");
        //1,将当前结点设置为已访问
        isVisited[i] = true;
        //2,取得i的第一个邻接结点w
        int w = getFirstNeighboor(i);
        //3，判断w是否存在,直到邻接节点不存在结束循环
        while (w!=-1){//不存在第一个邻接结点
            if(!isVisited[w]){//w没被访问
                dfs(isVisited, w);//4，以w为当前结点，递归深度优先遍历
            }

            //5,查找i的w邻接结点的下一个邻接节点
            w = getNextNeighbor(i,w);
        }
    }

    //深度优先遍历搜索算法：DFS
    //以上方法只从第i个顶点开始遍历，并没有所有顶点遍历,所以以下创建一个重载方法遍历所有结点
    public void dfs(){
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {//对所有结点进行深度优先遍历
            if(!isVisited[i]){//判断其是否访问过了
                dfs(isVisited,i);//从第i个顶点开始，深度优先遍历
            }
        }
    }

    //广度优先遍历搜索算法：BFS
    //对一个结点进行广度优先搜索算法
    private void bfs(boolean[] isVisited,int i){
        //创建下标u表示队列的头结点
        int u = 0;
        //创建邻接结点w
        int w = 0;
        //创建队列
        LinkedList queue = new LinkedList();

        //1,输出当前下标i对应结点
        System.out.print(getValueByIndex(i) + "->");
        //将下标为i的顶点标记为已访问
        isVisited[i] = true;

        //2,将i加入队列
        queue.addLast(i);
        //3,如果队列不为空，循环
        while (!queue.isEmpty()){
            //4 取出头结点u
            u = (Integer) queue.removeFirst();
            //5 得到u的第一个邻接结点w
            w = getFirstNeighboor(u);
            //6 如果w存在，则循环
            while (w!=-1){
                if(!isVisited[w]){
                    //6.1 如果w未被访问,则访问结点并标记访问
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;

                    //6.2 结点w入队列
                    queue.addLast(w);
                }
                //6.3 查找结点u的继w邻接结点后的下一个邻接结点
                w = getNextNeighbor(u, w);
            }
        }
    }

    //广度优先遍历搜索算法：BFS
    public void bfs(){
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //图中常用的方法
    //返回顶点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的的条数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回顶点i(下标)对应的值: 0 -> "A", 1->"B",2->"C",3->"D",4->"E"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值weight
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }


    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    /**
     * @param v1     表示第一个顶点的下标: 即顶点""A","B","C","D","E" 的v1依次是 0,1,2,3,4,5
     * @param v2     表示第二个顶点的下标: 即顶点""A","B","C","D","E" 的v2依次是 0,1,2,3,4,5
     * @param weight 表示顶点之间的有边，在邻接矩阵中表示某个值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;//表示例如A -> B
        edges[v2][v1] = weight;//表示例如B -> A
        numOfEdges++;//记录每次增加的边的数目
    }
}
