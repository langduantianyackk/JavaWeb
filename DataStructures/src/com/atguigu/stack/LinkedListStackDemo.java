package com.atguigu.stack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: ArrayStackDemo
 * @description: TODO
 * @date 2021/12/13 12:05
 **/
public class LinkedListStackDemo {
    public static void main(String[] args) {
        //测试栈的操作
        //创建栈
        LinkedListStack linkedListStack = new LinkedListStack(4);
        String key="";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("****************实现栈的操作***********************");
            System.out.println("1,请输入显示栈操作:show");
            System.out.println("2,请输入程序退出操作:exit");
            System.out.println("3,请输入入栈操作:push");
            System.out.println("4,请输入出栈操作:pop");
            System.out.println("5,请输入判断栈满操作:full");
            System.out.println("6,请输入判断栈空操作:empty");
            System.out.print("请输入以上操作：");
            key = scanner.next();
            switch(key){
                case "show":
                    linkedListStack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.print("请输入入栈的数据:");
                    int value = scanner.nextInt();
                    linkedListStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = linkedListStack.pop();
                        System.out.printf("出栈的数据是statc[%d]=%d\n",1,pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "full":
                    boolean full = linkedListStack.isFull();
                    if(full){
                        System.out.println("栈满");
                    }else{
                        System.out.println("栈未满");
                    }
                    break;
                case "empty":
                    boolean empty = linkedListStack.isEmpty();
                    if(empty){
                        System.out.println("栈空");
                    }else{
                        System.out.println("栈不为空");
                    }
                    break;
                default:
                    System.out.println("输入错误请重新输入!");
                    break;
            }
        }
        System.out.println("程序退出了！");
    }
}

//用链表模拟栈
class LinkedListStack{
    private StackNode head = new StackNode();//表示栈空的情况
    private int maxSize;//栈的大小

    //构造器初始化栈
    public LinkedListStack(int maxSize){
        StackNode temp = head;//借助辅助节点创建链表
        //创建链表模拟栈
        for(int i= 0; i<maxSize;i++){
            StackNode stackNode = new StackNode();

            //判断是否遍历到了最后一个节点
            if(temp.getNext() == null){
                //将要添加的节点添加到末尾
                StackNode next = temp.getNext();
                next = stackNode;
            }
            //后移使得temp每次指向带末尾元素
            temp = temp.getNext();
        }
    }

    //站满
    public boolean isFull(){
        //判断站满
        StackNode temp = head.getNext();
        boolean falg= false;//flag表示栈是否满了。默认false
        //遍历链表
        for(int i=0; i<maxSize-1;i++){
            if(temp.getNext() == null && temp.getValue() != -1){
                falg = true;
                break;
            }
            temp = temp.getNext();
        }

        return  false;
    }

    //栈空
    public boolean isEmpty(){
        //判断栈空
        if(head.getNext().getValue()==-1){
            return true;
        }
        return  false;
    }

    //入栈
    public void push(int value){
        //判读是否栈满
        if(isFull()){
            System.out.printf("栈已满,%d无法入栈\n",value);
            return;
        }
        //入栈操作
        StackNode temp = head.getNext();
        while(true){
            if(temp.getValue() == -1){
                temp.setValue(value);
                break;
            }
            temp = temp.getNext();
        }
    }

    //出栈
    public int pop(){
        //判断是否栈空
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法出栈");
        }

        StackNode temp = head.getNext();
        int[] value = new int[maxSize];
        int top = -1;
        for(int i=0; i<maxSize;i++){
            if(temp.getValue()!=-1){
                value[i] = temp.getValue();
            }
            temp = temp.getNext();
        }

        return 0;
    }

    //遍历栈,遍历时需要从栈顶遍历到栈低
    public void show(){
        //判断是否栈空
        if(isEmpty()){
            System.out.println("栈为空!");
            return;
        }
        //遍历栈
//        for(int i=top; i>=0;i--){
//            System.out.printf("stack[%d]=%d\n",i,stack[i]);
//        }
    }
}
//创建StackNode,表示一个链表的节点
class StackNode{
    private int value = -1;
    private StackNode next;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "value=" + value +
                '}';
    }
}
