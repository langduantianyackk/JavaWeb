package com.atguigu.linkedlist;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: SingleLinkedListTest
 * @description: TODO
 * @date 2021/12/10 16:45
 **/
public class SingleLinkedListTest {
    public static void main(String[] args) {
        //求单链表中有效节点的个数
        System.out.println("**************求单链表中有效节点的个数*********************");
        //创建节点
        Node node1 = new Node(5,"许嵩");
        Node node2 = new Node(4,"毛不易");
        Node node3 = new Node(2,"刘德华");
        Node node4 = new Node(6,"黎明");
        Node node5 = new Node(1,"张学友");
        Node node6 = new Node(3,"郭富城");
        SingleLinkedList1 list = new SingleLinkedList1();
        list.addByOrder(node1);
        list.addByOrder(node2);
        list.addByOrder(node3);
        list.addByOrder(node4);
        list.addByOrder(node5);
        list.addByOrder(node6);

        //有效节点的个数
        System.out.println(list.dataNode());

        //查找单链表中的倒数第 k 个结点
        System.out.println("**************查找单链表中的倒数第 k 个结点*********************");
        list.inverseNode(1);
        list.inverseNode(3);
        list.inverseNode(6);
        list.inverseNode(7);
        list.inverseNode(8);

    }
}
class SingleLinkedList1{
    Node head = new Node(0,"");

    // 求单链表中有效节点的个数
    public int dataNode(){

        int nodeSum=0;//定义有效节点个数变量默认为0
        Node temp = head;

        while(true){
            if(temp.next == null){
                break;
            }
            nodeSum++;

            temp = temp.next;
        }
        return  nodeSum;//返回有效节点个数
    }

    //按顺序添加节点到单链表中
    public void addByOrder(Node node){
        //1，首先的找到要插入入的节点temp？如何实现
        //使用辅助变量操作单链表
        Node temp = head;
        //遍历单链表，查找要按顺序插入的节点temp
        while(true){
            //判断要插入的点是否是最后一个元素,是则直接插入到最后一个节点的后面
            if(temp.next == null){
                temp.next = node;
                node.next = null;
                break;
            }
            //新节点的no在temp和temp.next的no之间，则找到插入点temp
            if(node.no <temp.next.no){
                //2,将新的节点.next=temp.next;
                node.next = temp.next;
                //3,temp.next = 新的节点;
                temp.next = node;
                break;
            }else if(node.no == temp.next.no){
                System.out.printf("准备插入的英雄编号%d已经存在,不能加入\n",node.no);
                break;
            }

            //temp后移是的每次temp和单链表上的节点指向同一个地址
            temp = temp.next;
        }
    }

    //查找单链表中的倒数第 k 个结点
    public void inverseNode(int k){
        //判断要查找单链表中的倒数第 k 个结点是和否存在
        int n = dataNode() - k + 1;//n表示倒数k多个节点对应的整数第n个节点(不算头结点)
        if(n <=0){
            System.out.printf("倒数第%d个结点不存在\n",k);
            return;
        }

        int count = 0;//每次循环到有效数据的第count个
        Node temp = head;
        while(true){
            if(n==count){//找到了
                System.out.println(temp);
                break;
            }
            count++;
            temp = temp.next;
        }
    }

    //单链表的反转【腾讯面试题，有点难度】
    public void inverse(){

    }
    //从尾到头打印单链表 【百度，要求方式 1：反向遍历 。 方式 2：Stack 栈】
    //合并两个有序的单链表，合并之后的链表依然有序【课后练习.】

}
//定义HeroNode，每一个HeroNode对象就是一个节点
class Node{
    public int no;
    public String name;
    public Node next;//指向下一个节点

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}