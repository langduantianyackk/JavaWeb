package com.atguigu.hashtab;

import java.util.Scanner;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: HashTabDemo
 * @description: TODO
 * @date 2021/12/29 9:40
 **/
public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表对象
        HashTab hashTab = new HashTab(7);

        //写一个菜单
        String key = "";//接收用户输入
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员信息");
            System.out.println("find:查找雇员信息");
            System.out.println("exit:退出系统");
            System.out.println("请输入上述相关操作：");
            key = scanner.next();
            int id = 0;
            switch (key){
                case "add":
                    System.out.println("请输入雇员id:");
                    id = scanner.nextInt();
                    System.out.println("请输入雇员姓名:");
                    String name = scanner.next();

                    //创建一个雇员
                    Emp emp = new Emp(id,name);

                    //添加雇员
                    hashTab.add(emp);
                    break;
                case "list":
                    //显示雇员信息
                    hashTab.list();
                    break;
                case "find":
                    //查找雇员信息
                    System.out.print("请输入要查找的雇员的id：");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;
            }
        }
    }
}
//表示一个哈希表,管理多条链表
class HashTab{
    //创建存放多条链表的数组
    private EmpLinkedList[] empLinkedListArr;//默认为null
    private int size;

    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化链表数组empLinkedListArr
        empLinkedListArr = new EmpLinkedList[size];
        //坑？分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }
    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应该添加到那条链表
        int empLinkedListNo = hashFun(emp.id);

        //将emp加入到链表数组下标为empLinkedListNo的链表中
        empLinkedListArr[empLinkedListNo].add(emp);
    }

    //遍历所有链表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i].list(i);
        }
    }

    //根据id查找雇员信息
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);//使用散列函数确定到那条链表中查找
        Emp emp = empLinkedListArr[empLinkedListNo].findEmpById(id);//调用链表的查找方法返回Emp
        //没找到
        if (emp == null) {
            System.out.printf("在哈希表中查找的id=%d的雇员不存在！\n", id);
        } else {//找到了
            System.out.printf("在第%d条链表找到了该雇员，id=%d\n",(empLinkedListNo+1), emp.id);
        }
    }

    //编写散列函数，目的可以通过id判断该员工应该添加到那条链表中
    //使用简单取模法
    public int hashFun(int id){
        return id % size;
    }
}
//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//next默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList单链表
class EmpLinkedList{
    //因为头指针指向第一个Emp，所以head指向第一个Emp
    private Emp head;//默认为null

    //添加雇员到链表
    //说明：1，假设添加雇员时，id是自增长的，那么直接添加到链表最后
    public void add(Emp emp){
        //如果添加的是第一个雇员
        if(head==null){
            head = emp;
            return;
        }
        //如果添加的不是第一个雇员，定义辅助节点temp指向head
        Emp temp = head;
        //遍历当前链表
        while (true){
            if(temp.next==null){//找到最后一个节点temp
                temp.next = emp;//将emp添加到最后一个节点末尾
                break;
            }

            //temp后移
            temp = temp.next;
        }
    }

    //遍历链表的雇员信息
    public void list(int no){
        //判断链表是否为空
        if(head == null){
            System.out.println("第"+(no+1)+"链表为空！");
            System.out.println();
            return;
        }

        System.out.println("第"+(no+1)+"链表的信息为：");
        //定义辅助节点temp指向head，while循环遍历链表
        Emp temp = head;
        while (true){
            //输出当前节点的信息
            System.out.println("id="+temp.id+",name="+temp.name);

            //遍历到当前节点为最后一个节点，结束
            if(temp.next == null){
                break;
            }

            //后移
            temp = temp.next;
        }
        System.out.println();//输出多条链表信息
    }

    //根据id查找雇员
    //说明：如果查找到了，返回Emp，否则，返回null
    public Emp findEmpById(int id){
        if(head==null){
            System.out.println("链表为空");
            return null;
        }

        //定义辅助节点temp指向head
        Emp temp = head;
        boolean flag = false;//定义flag表示是否存在该雇员，默认false
        //我的写法
//        while (true){//循环遍历链表
//            if(temp.id == id){//如果找到了该雇员
//                flag = true;
//                break;
//            }
//
//            if(temp.next == null){//判断是否是最后一个元素
//                break;
//            }
//
//            temp = temp.next;//后移
//        }
//
//        if(flag){
//            return temp;
//        }else {
//            return null;
//        }

        //老师写法
        while (true){//循环遍历链表
            if(temp.id == id){//如果找到了该雇员
                break;
            }

            if(temp.next == null){//判断是否是最后一个元素
                temp = null;
                break;
            }

            temp = temp.next;//后移
        }

        return  temp;
    }
}