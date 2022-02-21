package com.atguigu.tree;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: ArrBinaryTree
 * @description: TODO
 * @date 2021/12/31 9:50
 **/
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        //创建顺序存储二叉树的对象
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();//1,2,4,5,3,6,7
    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树
class ArrBinaryTree {
    private int[] arr;//存储二叉树结点数据的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder()
    public void preOrder(){
        preOrder(0);
    }
    //编写方法，完成顺序存储二叉树的前序遍历
    /**
     * @param index 数组下标
     */
    private void preOrder(int index) {
        //如果数组为空或arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }

        //按照二叉树前序遍历数组
        System.out.println(arr[index]);//输出父结点
        //向左子树递归
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右子树递归
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //编写方法，完成顺序存储二叉树的中序遍历
    //编写方法，完成顺序存储二叉树的后序遍历
}


