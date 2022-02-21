package com.atguigu.stack;

import java.util.Scanner;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: ArrayStackDemo
 * @description: TODO
 * @date 2021/12/13 12:05
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试栈的操作
        //创建栈
        ArrayStack arrayStack = new ArrayStack(4);
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
                    arrayStack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.print("请输入入栈的数据:");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();
                        System.out.printf("出栈的数据是statc[%d]=%d\n",arrayStack.getTop()+1,pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "full":
                    boolean full = arrayStack.isFull();
                    if(full){
                        System.out.println("栈满");
                    }else{
                        System.out.println("栈未满");
                    }
                    break;
                case "empty":
                    boolean empty = arrayStack.isEmpty();
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
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//使用数组模拟栈,数据就放在数组中
    private int top = -1;//定义一个变量top表示栈顶，默认-1

    public int getTop() {
        return top;
    }

    //构造器初始化栈
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new  int[maxSize];
    }

    //站满
    public boolean isFull(){
        //判断站满条件
//        if(top==maxSize-1){
//            return true;
//        }
//        return false;
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        //判断栈空
//        if(top == -1){
//            return true;
//        }
//        return false;
        return top == -1;
    }

    //入栈
    public void push(int value){
        //判读是否栈满
        if(isFull()){
            System.out.printf("栈已满,%d无法入栈\n",value);
            return;
        }
        //入栈操作
//        top++;
//        stack[top] = value;
        stack[++top] = value;
    }

    //出栈
    public int pop(){
        //判断是否栈空
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法出栈");
        }
        return stack[top--];
    }

    //遍历栈,遍历时需要从栈顶遍历到栈低
    public void show(){
        //判断是否栈空
        if(isEmpty()){
            System.out.println("栈为空!");
            return;
        }
        //遍历栈
        for(int i=top; i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
