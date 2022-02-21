package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: CircleArrayQueue
 * @description: TODO
 * @date 2021/12/9 15:16
 **/
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试数组模拟环形队列
        //创建环形队列
        CircleArrayQueue queue = new CircleArrayQueue(3);
        char key = ' ';//从键盘接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println();
            System.out.println("********队列操作界面********");
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中获取数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.print("请输入以上操作:");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's'://显示队列
                    queue.showQueue();
                    break;
                case 'a'://添加数据到队列
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\t\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看头数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("查看队列头数据是%d\t\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

//使用数组模拟环形队列-编写一个ArrayQueue类
//实现思路：定义下标front是指向队的第一个元素,rear指向队列尾部的数据的后一个数据的索引
//然后预留一个空间,rear指向这个预留空间，假设队存放5个元素，那么底层数组容量为capacity=6
//有效数据(队满时的元素个数)size=5，当front=1,最后一个元素的索引位置为5，rear不能直接++
// 因为环形队列，所以最后一个元素的索引位置为5的下一个索引为0，即rear=0，此时front=rear +1
//那么队满的条件为（rear+1）%capacity == front
//队空条件为：rear == front
class CircleArrayQueue {
    private int maxSize;//表示数组(队列)的最大容量
    private int front;//表示队列的头部下标
    private int rear;//表示队列的尾部下标
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize + 1];//因为要预留一个位置给rear，所以队列加入方5个元素，底层容量为6
        front = 0;//指向队列头部(分析出front是指向队的第一个元素)
        rear = 0;//指向队列尾部，(分析出rear指向队列尾部的数据的后一个数据的索引)
    }

    //判断队列是否满
    public Boolean ifFull() {
        return (rear + 1) % arr.length == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是和否满
        if (ifFull()) {
            System.out.println("队列已满，添加失败");
            return;
        }

        arr[rear] = n;//往rear的位置添加元素，rear的值发生改变，必须考虑取模
        rear = (rear + 1) % arr.length;
    }

    //获取队列的数据,出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
//            System.out.println("队列为空，获取数据失败");
            throw new RuntimeException("队列为空，获取数据失败");
        }

        int value = arr[front];
        front = (front + 1) % arr.length;//front后移必须考虑取模，否则角标越界
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }

        //思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size() ; i++) {
                System.out.printf("arr[%d]=%d\t", i%arr.length, arr[i%arr.length]);
        }
    }

    //显示队列的头数据，注意不是取数据
    public int headQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }

        //front是指向队列第一个元素
        return arr[front];
    }

    //求出当前环形队列有效数据个数
    public int size(){
        return (rear+arr.length-front)%arr.length;
    }
}
