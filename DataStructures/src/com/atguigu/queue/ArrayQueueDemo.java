package com.atguigu.queue;

import java.util.Queue;
import java.util.Scanner;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: ArrayQueueDemo
 * @description: TODO
 * @date 2021/12/7 12:03
 **/
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试队列
        //创建队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//从键盘接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println();
            System.out.println("********队列操作界面********");
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中获取数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.print("请输入以上操作:");
            key = scanner.next().charAt(0);
            switch(key){
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
                        System.out.printf("取出的数据是%d\t\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看头数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("查看队列头数据是%d\t\n",res);
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
//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;//表示数组(队列)的最大容量
    private int front;//表示队列的头部下标
    private int rear;//表示队列的尾部下标
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部(分析出front是指向队列头的前一个位置)
        rear = -1;//指向队列尾部，指向队列尾部的数据(指向队列最后一个数据)
    }

    //判断队列是否满
    public Boolean ifFull(){
        return  rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return  front == rear;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是和否满
        if(ifFull()){
            System.out.println("队列已满，添加失败");
            return;
        }

//        rear++;
//        arr[rear] = n;
        arr[++rear] = n;
    }

    //获取队列的数据,出队列
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
//            System.out.println("队列为空，获取数据失败");
            throw new RuntimeException("队列为空，获取数据失败");
        }

//        front++;
//        data = arr[front];
        int value = arr[++front];
        arr[front] = 0;

        if((rear==front)&&(rear==maxSize-1)){
            rear = -1;
            front = -1;
        }

        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        //判断队列是否为空
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }

        for(int i=0; i<arr.length;i++){
            System.out.printf("arr[%d]=%d\t",i,arr[i]);
        }
    }

    //显示队列的头数据，注意不是取数据
    public int headQueue(){
        //判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }

        //front是指向对头的前一个位置
        return arr[front+1];
    }
}
