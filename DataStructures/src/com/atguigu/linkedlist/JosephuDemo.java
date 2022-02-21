package com.atguigu.linkedlist;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: JosephuDemo
 * @description: TODO
 * @date 2021/12/11 16:50
 **/
public class JosephuDemo {
    public static void main(String[] args) {
        //环形队列测试
        //创建环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //构建环形链表
        circleSingleLinkedList.add(5);//加入5个小孩
        //输出环形链表
        circleSingleLinkedList.show();

        //环形队列小孩出圈顺序
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建环形单项链表
class CircleSingleLinkedList{
    //创建一个first节点,当前没有编号
    private Boy first = null;

    //构建环形链表
    public void add(int nums){
        //nums:环形队列节点的个数，进行校验
        if(nums<1){
            System.out.printf("%d的值不合法\n",nums);
            return;
        }

        Boy curBoy = null;//辅助指针，构建环形链表
        //创建环形链表
        for(int i=1;i<=nums;i++){
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);//每次循环都创建一个小孩节点
            //1，如果是第一个小孩，则考虑闭环
            if(i==1){
                first = boy;//first指向第一个小孩
                first.setNext(first);//第一个小孩自己形成闭环
                curBoy = first;//辅助节点指向第一个小孩
            }else{
                //2,不是一个小孩，考虑闭环
                curBoy.setNext(boy);
                boy.setNext(first);
//                curBoy = curBoy.getNext();
                curBoy = boy;
            }
        }
    }

    //根据用户输入，计算小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数
     * @param countNum  表示数几下
     * @param nums  表示环形链表中小孩个数
     */
    public void countBoy(int startNo,int countNum, int nums){
        //对参数数据进行校验
        if(first == null){
            System.out.println("环形链表为空");
            return;
        }

        if(nums<1){
            System.out.printf("输入的%d错误，圈中的小孩的人数不能小于1\n",nums);
            return;
        }

        if(startNo<1 || startNo > nums){
            System.out.printf("圈中的第%d个小孩不存在\n",startNo);
            return;
        }

        if(countNum < 1){
            System.out.println("每次报数的次数不能小于1");
            return;
        }

        //创建辅助指针完成小孩圈
        Boy helper = first;

        //1，遍历查找helper即最后一个元素的位置
        while(true){
            if(helper.getNext() == first){//此时的helper便是从first开始的最后一个节点
                break;
            }
            //后移
            helper = helper.getNext();
        }

        //2，移动到第一次报数点
        //小孩报数之前，为了确定移动到第一次开始报数的位置，先让first和helper移动startNo-1次
        for(int i=0; i<startNo-1;i++){
            first = first.getNext();
            helper = helper.getNext();
        }

        //3，从报数点开始报数，小孩出圈，重复
        //通过每次循环，找到出圈小孩出圈
        while(true){
            //判断是否是最后一个出圈小孩(作为循环终止的条件)
            if(first == helper){
                System.out.printf("最后一个出圈的小孩编号为%d\n",first.getNo());
                break;
            }


            //当小孩报数时，让first和helper指针同时移动countNum -1次，然后出圈，然后循环，继续同样操作
            for(int i=0; i<countNum-1;i++){
                first = first.getNext();
                helper = helper.getNext();
            }

            //小孩出圈
            //1,输出小孩编号
            System.out.printf("要出圈的小孩编号为%d\n",first.getNo());//每次循环小孩出圈操作

            //2，删除出圈节点：让first指向出圈小孩的下一个节点，以便重新开始报数，
            // 让helper的next域指向first实现删除出圈小孩，此时第一次循环完成，继续从first开始重复前面步骤
            first = first.getNext();
            helper.setNext(first);
        }
    }
    //删除环形链表的元素
    //k表示从第几个元素开始报数
    //m表示移动的步数
    public void delete(int k,int m){
        k= 1;  m=2;
        if(first == null){
            System.out.println("环形链表为空");
            return;
        }

        Boy helper = first;

        while(true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        for (int i=1; i<=m-1;i++){
            helper = helper.getNext();
            first = first.getNext();
        }

        first = first.getNext();
        helper.setNext(first);
    }

    //遍历环形链表
    public void show(){
        //判断环形链表是否为空
        if(first == null){
            System.out.println("环形链表为空");
            return;
        }

        Boy curBoy = first;//辅助节点curBoy实现从fist开始遍历
        //遍历
        while(true){
            System.out.printf("小孩的编号为%d\n",curBoy.getNo());//循环输出节点信息

            //判断是否遍历到环形链表末尾
            if(curBoy.getNext()==first){
                break;
            }

            curBoy = curBoy.getNext();//curBoy后移
        }
    }
}

//定义一个Boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//表示下一个节点地址，默认是null

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
