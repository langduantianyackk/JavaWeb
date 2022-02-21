package com.atguigu.linkedlist;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: DoubleLinkedListDemo
 * @description: TODO
 * @date 2021/12/11 9:33
 **/
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //双向链表测试
        //创建节点
        HeroNode1 heroNode1 = new HeroNode1(1, "宋江", "及时雨");
        HeroNode1 heroNode2 = new HeroNode1(2, "卢俊义", "玉麒麟");
        HeroNode1 heroNode3 = new HeroNode1(3, "吴用", "智多星");
        HeroNode1 heroNode4 = new HeroNode1(4, "林冲", "豹子头");

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        System.out.println("***********双向链表实现添加(默认加到末尾)**************************");
        //添加节点
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode1);
        //由于默认添加到末尾添加节点的的顺序为：
        //heroNode2->heroNode4->heroNode3->heroNode1->heroNode5(heroNode3)->heroNode1...构成死循环
//        HeroNode1 heroNode5 = heroNode3;
//        doubleLinkedList.add(heroNode5);

        //输出节点
        doubleLinkedList.show();

        System.out.println("***********双向链表实现添加(按编号顺序)**************************");
        HeroNode1 heroNode5 = new HeroNode1(1, "宋江", "及时雨");
        HeroNode1 heroNode6 = new HeroNode1(2, "卢俊义", "玉麒麟");
        HeroNode1 heroNode7 = new HeroNode1(3, "吴用", "智多星");
        HeroNode1 heroNode8 = new HeroNode1(4, "林冲", "豹子头");
        //创建双向链表
        DoubleLinkedList doubleLinkedList1 = new DoubleLinkedList();
        //添加节点
        doubleLinkedList1.addByOrder(heroNode8);
        doubleLinkedList1.addByOrder(heroNode5);
        doubleLinkedList1.addByOrder(heroNode7);
        doubleLinkedList1.addByOrder(heroNode6);

        //输出节点
        doubleLinkedList1.show();

        //删除节点
        System.out.println("***********双向链表实现删除**************************");
        System.out.println("删除前：");
        doubleLinkedList.show();

        doubleLinkedList.delete(3);
//        doubleLinkedList.delete(1);
//        doubleLinkedList.delete(2);
//        doubleLinkedList.delete(4);
        System.out.println("删除后：");
        doubleLinkedList.show();

        //修改节点
        System.out.println("***********双向链表实现删除**************************");
        System.out.println("修改前：");
        doubleLinkedList.show();

        heroNode4 = new HeroNode1(4, "公孙胜", "入云龙");
        doubleLinkedList.updateNode(heroNode4);

        System.out.println("修改后：");
        doubleLinkedList.show();
    }
}

//创建一个双向链表类
class DoubleLinkedList{
    private HeroNode1 head = new HeroNode1(0,"","");//定义双向链表头结点

    //返回头结点
    public HeroNode1 getHead(){
        return head;
    }

    //方式一：实现双向链表的添加(默认添加到链表末尾)
    public void add(HeroNode1 node){
        //1,先找到双向链表的最后
        HeroNode1 temp = head;

        //遍历双向链表，查找双向链表最后一个元素
        while(true){

            //判断是否是最后一个元素
            if(temp.next == null){
                //2.进行添加操作
                temp.next = node;
//                node.next = null;
                node.pre = temp;
                break;
            }
            temp = temp.next;
        }
    }

    //方式二：实现双向链表的添加(根据编号的顺序)
    //根据节点中的no顺序添加
    public void addByOrder(HeroNode1 newHeroNode){
        HeroNode1 temp = head;//
        boolean flag = false;//flag表时添加的编号是否已经存在，默认false

        //遍历双向链表
        while(true){
            //判断是否遍历到最后一个元素，是则节点插入到最后元素末尾
            if(temp.next == null){
                break;
            }

            //将temp作为要插入的点，判断temp的no和新节点no比较，
            if(temp.next.no > newHeroNode.no){
                break;
            }else if(temp.next.no == newHeroNode.no){//判断编号是和否已经存在
                flag = true;
                break;
            }

            //后移
            temp = temp.next;
        }

        if(flag){
            System.out.printf("要插入的英雄信息的编号%d已经存在，添加失败\n",newHeroNode.no);
        }else{
            if(temp.next!=null){
                newHeroNode.next = temp.next;
                temp.next.pre = newHeroNode;
            }
            temp.next = newHeroNode;
            newHeroNode.pre = temp;
        }

    }

    //实现双向链表的节点删除
    //根据no删除
    public void delete(int no){
        HeroNode1 temp = head;//借助辅助节点遍历双向链表
        //遍历双向链表
        while(true){

            //遍历到最后一个元素，仍然没有找到
            if(temp == null){
                System.out.printf("要删除的英雄信息的编号%d不存在",no);
                break;
            }

            //找到了要删除的点
            if(temp.no == no){
                //进行删除操作
                temp.pre.next = temp.next;

                //判断如果删除的不是最后一个元素
                if(temp.next!= null){
                    temp.next.pre = temp.pre;
                }

                break;
            }

            temp = temp.next;
        }

    }

    //实现双向链表的节点修改
    public void updateNode(HeroNode1 newHeroNode){
        HeroNode1 temp = head;
        boolean flag = false; //表示要修改的编号是否存在，默认为false
        //借助辅助节点temp遍历单链表
        while(true){
            //查找单链表节点的no和要修改no是否相同
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }

            //判断是否遍历到最后一个元素，还没找到
            if(temp.next == null){
                break;
            }

            //后移temp实现节点遍历
            temp = temp.next;
        }

        if(flag){
            temp.no = newHeroNode.no;
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            System.out.printf("修改编号为%d的英雄信息不存在\n",newHeroNode.no);
        }
    }

    //显示双向链表
    public  void show(){
        //判断双向链表是否为空
        if(head.next == null){
            System.out.println("双向链表为空！");
            return;
        }
        //1,先找到双向链表的最后
        HeroNode1 temp = head.next;

        //遍历双向链表，查找双向链表最后一个元素
        while(true){

            System.out.println(temp);

            //判断是否是最后一个元素
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
    }
}

//定义HeroNode，每一个HeroNode对象就是一个节点
class HeroNode1 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode1 next;//指向下一个节点
    public HeroNode1 pre;//指向前一个节点

    public HeroNode1(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}