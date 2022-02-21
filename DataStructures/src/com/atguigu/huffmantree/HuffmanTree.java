package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: HuffmanTree
 * @description: TODO
 * @date 2022/1/3 16:04
 **/
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        createHuffmanTree(arr);
    }

    //创建赫夫曼树
    public static Node createHuffmanTree(int[] arr) {
        //第一步,将数组元素构建成Node
        //1,遍历arr数组
        //2,将arr的每个元素构建成一个Node
        //3,将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //第二步，赫夫曼树的构建过程
        //构建过程是循环的
        while (nodes.size() > 1) {
            //第1步，对list从小到大排序
            Collections.sort(nodes);//使用工具了Collections中的sort

            System.out.println("nodes=：" + nodes);

            //第2步，去除根结点权值最小的两个数
            //1),去除权值最小的结点
            Node leftNode = nodes.get(0);
            //2),去除权第二小的结点
            Node rightNode = nodes.get(1);
            //3),构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4)已经构建完了两个结点，从ArrayL中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5)新构建的父结点加入到Arraylist中
            nodes.add(parent);
            Collections.sort(nodes);
        }

        //返回赫夫曼树的头(二叉树根结点)
        return nodes.get(0);
    }
}

//创建结点类
//为了让Node对象持续排序Collections集合排序，必须实现Comparable接口
class Node implements Comparable<Node> {
    int value;//结点的权值
    Node left;//指向左子结点
    Node right;//指向右子结点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.value, o.value);//表示从小到大排，前面加负号-表示从大到小排
    }
}
