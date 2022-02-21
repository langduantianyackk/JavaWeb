package com.atguigu.linkedlist;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: SingleLinkedList
 * @description: TODO
 * @date 2021/12/9 17:07
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建几个节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        //创建节点并将以上节点加入链表中
        SingleLinkedList sll = new SingleLinkedList();

        ////第一种方法在添加英雄时，直接添加到链表的尾部
        sll.addNode(heroNode1);
        sll.addNode(heroNode4);
        sll.addNode(heroNode2);
        sll.addNode(heroNode3);


        //输出链表
        System.out.println("输出按英雄添加顺序英雄信息：");
        sll.showLinkedList();

        System.out.println("******************添加节点信息*******************************");

        //第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);

        //输出链表
        System.out.println("输出按英雄编号排序的英雄信息：");
        sll.showLinkedList();

        System.out.println("******************添加节点信息*******************************");
        //老师做法
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder1(heroNode4);
        list.addByOrder1(heroNode1);
        list.addByOrder1(heroNode3);
        list.addByOrder1(heroNode2);
        list.addByOrder1(heroNode4);

        //输出链表
        System.out.println("输出按英雄编号排序的英雄信息：");
        list.showLinkedList();


        System.out.println("******************修改节点信息*******************************");
        //根据提供的节点信息修改节点
        System.out.println("修改前：");
        System.out.println("输出按英雄编号排序的英雄信息：");
        singleLinkedList.showLinkedList();
        HeroNode heroNode5 = new HeroNode(4, "公孙胜", "入云龙");
        HeroNode heroNode6 = new HeroNode(5, "关胜", "大刀");

        singleLinkedList.updateNode(heroNode5);
        singleLinkedList.updateNode(heroNode6);
        //输出链表
        System.out.println("修改后：");
        System.out.println("输出按英雄编号排序的英雄信息：");
        singleLinkedList.showLinkedList();

        //删除节点信息
        System.out.println("******************删除节点信息*******************************");
        System.out.println("删除前：");
        System.out.println("输出按英雄编号排序的英雄信息：");
        singleLinkedList.showLinkedList();

        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(4);
        singleLinkedList.deleteNode(2);
        singleLinkedList.deleteNode(3);

        System.out.println("删除后：");
        System.out.println("输出按英雄编号排序的英雄信息：");
        singleLinkedList.showLinkedList();
    }
}

//定义SingleLinkedList单链表 管理英雄
class SingleLinkedList{
    //先初始化一个头节点。头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头结点
    public HeroNode getHead(){
        return head;
    }

    //第一种方法在添加英雄时，直接添加到链表的尾部
    //添加节点到单项链表
    //思路：当不考虑编号的顺序时
    //1,找到当前链表的尾节点
    //2,将尾结点的next指向新添加的节点
    public void addNode(HeroNode heroNode){
        //head节点不能动，需要一个辅助节点temp
        HeroNode temp = head;
        //遍历链表找到最后
        while(true){
            //如果节点的next指针为null，找到了尾结点
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }

        //当退出while循环之后,temp就指向了尾结点
        //将尾结点的next指向新的节点
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
    //思路分析：1，首先的找到要插入入的节点temp？如何实现
    //2,将新的节点.next=temp.next;
    //3,temp.next = 新的节点;

    public void addByOrder(HeroNode heroNode){
        //1，首先的找到要插入入的节点temp？如何实现
        //使用辅助变量操作单链表
        HeroNode temp = head;
        //遍历单链表，查找要按顺序插入的节点temp
        while(true){
            //判断要插入的点是否是最后一个元素,是则直接插入到最后一个节点的后面
            if(temp.next == null){
                temp.next = heroNode;
                heroNode.next = null;
                break;
            }
            //新节点的no在temp和temp.next的no之间，则找到插入点temp
            if(heroNode.no <temp.next.no){
                //2,将新的节点.next=temp.next;
                heroNode.next = temp.next;
                //3,temp.next = 新的节点;
                temp.next = heroNode;
                break;
            }else if(heroNode.no == temp.next.no){
                System.out.printf("准备插入的英雄编号%d已经存在,不能加入\n",heroNode.no);
                break;
            }

            //temp后移是的每次temp和单链表上的节点指向同一个地址
            temp = temp.next;
        }
    }
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
    //思路分析：1，首先的找到要插入入的节点temp？如何实现
    //2,将新的节点.next=temp.next;
    //3,temp.next = 新的节点;
    //老师的代码实现
    public void addByOrder1(HeroNode heroNode){
        //1，首先的找到要插入入的节点temp？如何实现
        //使用辅助变量操作单链表
        HeroNode temp = head;
        boolean flag = false;//flag表示添加的编号是否存在，默认为false
        //遍历单链表，查找要按顺序插入的节点temp
        while(true){
            //判断要插入的点是否是最后一个元素,是则直接插入到最后一个节点的后面
            if(temp.next == null){
                break;
            }
            //新节点的no在temp和temp.next的no之间，则找到插入点temp
            if(heroNode.no <temp.next.no){
                break;
            }else if(heroNode.no == temp.next.no){
                flag = true;//编号已经存在，不允许插入
                break;
            }

            //temp后移是的每次temp和单链表上的节点指向同一个地址
            temp = temp.next;
        }

        //判断flag.如果为true，编号存在，如果false，则需要插入新节点
        if(flag){
            System.out.printf("准备插入的英雄编号%d已经存在,不能加入\n",heroNode.no);
        }else{
            //2,将新的节点.next=temp.next;
            heroNode.next = temp.next;
            //3,temp.next = 新的节点;
            temp.next = heroNode;
        }
    }

    //修改节点信息,根据no修改，即no不能改
    //根据给定的节点信息的no修改节点
    public void updateNode(HeroNode newHeroNode){
        HeroNode temp = head;
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

    //删除节点信息,根据no删除，即no不能改
    //根据提供的节点信息的no删除
    public void deleteNode(int no){

        //因为head节点不能动，所以借助辅助节点temp
        HeroNode temp = head;
        //flag表时要删除的节点的no是否存在，默认false
        boolean flag = false;

        //遍历单链表
        while(true){
            //判断temp是否是最后一个节点,是则要删除的节点不存在
            if(temp.next == null){
                break;
            }

            //将temp.next作为要删除的节点，判断temp.next.no是否等于要删除的节点的no,那么temp,temp.next,temp.next.next
            //如果将temp作为要删除的节点，则它的上一个节点的地址无法获取
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //要删除的节点存在
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("要删除编号为%d的英雄信息不存在\n",no);
        }
    }

    //显示链表[遍历]
    public void showLinkedList(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;//将temp指向第一个节点

        //当不是尾结点是输出节点信息
        while(temp.next!=null){
            //输出下一个节点的信息
            System.out.println(temp);
            //temp后移
            temp = temp.next;
        }

        //输出最后一个节点的信息
        if(temp.next==null){
            System.out.println(temp);
        }
    }
}
//定义HeroNode，每一个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
