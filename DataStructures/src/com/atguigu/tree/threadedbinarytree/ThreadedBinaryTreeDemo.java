package com.atguigu.tree.threadedbinarytree;
/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: ThreadedBinaryTreeDemo
 * @description: TODO
 * @date 2021/12/31 12:01
 **/
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        //创建线索二叉树的对象
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        //创建结点
        HeroNode root = new HeroNode(1, "Tom");
        HeroNode node2 = new HeroNode(3, "Jerry");
        HeroNode node3 = new HeroNode(6, "leo");
        HeroNode node4 = new HeroNode(8, "smith");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "Tim");
        //手动构建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //设置根结点
        threadedBinaryTree.setRoot(root);

        //二叉树线索化
        threadedBinaryTree.threadedNodes();

        //测试线索化，以node5为例
//        System.out.println("node5前驱结点:" + node5.getLeft());//3
//        System.out.println("node5前驱结点的类型type=" + node5.getLeftType());//1
//        System.out.println("node5后继结点:" + node5.getRight());//1
//        System.out.println("node5后继结点的类型type=" + node5.getLeftType());//1

        //由于二叉树被线索化，导致叶子结点有左指针和右指针不一定为null,
        // 所以用原来前序，中序，后序遍历出现死循环
        //测试
//        threadedBinaryTree.preOrder();//StackOverflowError
//        threadedBinaryTree.infixOrder();//StackOverflowError
//        threadedBinaryTree.postOrder();//StackOverflowError

        //遍历线索化二叉树
        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();
    }
}

//定义ThreadedBinaryTree  实现了线索二叉树
class ThreadedBinaryTree {
    private HeroNode root;//指向根结点
    //为了实现线索化,创建pre表示当前结点node的前驱结点的指针
    //在递归进行线索化时，pre总是保留为当前结点node的前一个结点
    private HeroNode pre = null;//

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //线索化之后中前序序遍历二叉树
    //线索化之后中序遍历二叉树
    //中序遍历
    public void threadedList() {
        //因为遍历时不能更改二叉树已有的指针，所以定义辅助结点node,从root开始遍历
        HeroNode node = root;
        while(node!=null){//当每个结点不为null时遍历
            //循环遍历找到leftType == 1的结点，而找到的第一个为8结点即为开始遍历的第一个节点
            //后面点随着遍历而变化，当leftType == 1，说明该结点是线索化的
            //1,找到线索化的点第一个点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            //2,打印当前结点
            System.out.println(node);

            //3,判断当前结点如果有后继结点，就一直输出
            while (node.getRightType() == 1){
                node = node.getRight();//当前结点后移
                //打印当前结点
                System.out.println(node);
            }

            //4,此时，说明当前结点没有后继节点了，让当前结点指向右子树
            node = node.getRight();//此时赋值之前(右边的)的node一定有右子树
        }

    }

    //线索化之后后序遍历二叉树

    //重载threadedNodes()方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(HeroNode node){
        //如果node为空，表示不能线索化
        if(node==null){
            return;
        }

        //根据中序进行线索化
        //1,先线索化左树
        threadedNodes(node.getLeft());
        //2,然后线索化当点节点
        //2.1先线索化前驱结点
        //第一次访问，以当前结点为8结点为例  8结点.left == null 8结点.left == pre  8结点.leftType = 1
        if(node.getLeft()==null){//如果当前结点没有左子树才能设置前驱结点
            node.setLeft(pre);//将node的前驱指向pre
            node.setLeftType(1);//1,表示此时的left指向前驱结点，0表示此时的left指向左子树
        }
        //2.2后线索化后继结点
        //pre==null第一次访问不会进，第二次访问时以当前结点3结点，前驱结点8结点为例  8结点.right == null 8结点.right == node  8结点.rightType = 1
        if(pre!=null && pre.getRight()==null){//如果当前结点的前驱结点pre(按照中序遍历是的遍历到当前结点的前一个节点)没有右子树才能设置后继结点
            pre.setRight(node);//将当前结点的确前驱结点pre指向node，即指向pre的后继结点
            pre.setRightType(1);//1,表示此时的right指向后继结点，0表示此时的right指向右子树
        }

        //当前node遍历到下一个结点之前，保留下一个结点node的前驱结点即pre指向当前node
        pre = node;

        //2,再线索化右树
        threadedNodes(node.getRight());

    }


    //删除结点
    public void delNode(int no){
        if(this.root!=null){
            //如果只有一个结点，则立即判断root是不是要删除的结点
            if(this.root.getNo() == no){
                this.root = null;
            }
//            else if(this.root.getLeft() == null && this.root.getRight()==null){
//
//            }
            else {
                //递归删除
                this.root.delNode(no);
            }

        }else {
            System.out.println("空树，不能删除");
        }

    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法前序遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法中序遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法后序遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(this.root!=null){
            return this.root.preOrderSearch(no);
        }
        return null;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(this.root!=null){
            return this.root.infixOrderSearch(no);
        }
        return null;
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(this.root!=null){
            return this.root.postOrderSearch(no);
        }
        return null;
    }

}
//创建HeroNode结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//指向左结点，默认为null
    private HeroNode right;//指向右结点，默认为null

    //说明
    //1,如果leftType==0，表示该结点指向的左子树，如果等于1,表示指向前驱结点
    //2,如果rightType==0，表示该结点指向的右子树，如果等于1,表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除结点
    //1，如果删除的是叶子结点，就直接删除
    //2，如果删除的是非叶子结点，就删除该子树
    /*
    思路：
    1，因为二叉树是单向的，所有应当把当前结点的子节点作为要删除的节点，而不是把当前结点作为要删除的结点
    2，如果当前结点的左子结点不为空，并且左子结点就是要删除的点，那么this.left == null,并且就返回（结束递归删除）
    3,如果当前结点的右子结点不为空，并且右子结点就是要删除的点，那么this.right == null,并且就返回（结束递归删除）
    4,如果第2步和第3步没有删除结点，那么就向左子树进行递归删除
    5,如果第4步也没有删除结点，那么就向右子树进行递归删除
     */
    public void delNode(int no) {
        //2,如果当前结点的左子结点不为空，并且左子结点就是要删除的点，
        // 那么this.left == null,并且就返回（结束递归删除）
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        //3,如果当前结点的右子结点不为空，并且右子结点就是要删除的点，
        //那么this.right == null,并且就返回（结束递归删除）
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        //4,如果第2步和第3步没有删除结点，那么就向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //5,如果第4步也没有删除结点，那么就向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //编写前序遍历方法
    public void preOrder() {
        System.out.println(this);//输出父结点
        if (this.left != null) {//判断左子结点是否为空
            this.left.preOrder();//递归向左子树前序遍历
        }
        if (this.right != null) {//判断右子结点是否为空
            this.right.preOrder();//递归向右子树前序遍历
        }
    }

    //编写中序序遍历方法
    public void infixOrder() {
        if (this.left != null) {//判断左子结点是否为空
            this.left.infixOrder();//递归向左子树中序遍历
        }
        System.out.println(this);//输出当前结点
        if (this.right != null) {//判断右子结点是否为空
            this.right.infixOrder();//递归向右子树中序遍历
        }
    }

    //编写后序遍历方法
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();//递归向左子树后序遍历
        }
        if (this.right != null) {
            this.right.postOrder();//递归向右子树后序遍历
        }
        System.out.println(this);//输出当前结点
    }

    //前序遍历查找

    /**
     * @param no 查找的no
     * @return 如果找到就返回该Node，否则返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序遍历~~");
        if (this.no == no) {//1，先判断当前结点no是否等于要查找的结点no
            return this;//2，如果相等，则返回当前结点
        }

        //3，如果不相等，那么判断当前结点的左子结点是否为空，
        //如果不为空，当前左子结点递归前序遍历查找
        if (this.left != null) {
            HeroNode leftheroNode = this.left.preOrderSearch(no);
            //4，如果向左递归前序查找，找到结点，返回，
            if (leftheroNode != null) {
                return leftheroNode;
            }
        }

        //判断当前结点的右子结点是否为空，
        //如果不为空，当前右子结点递归前序遍历查找
        if (this.right != null) {
            return this.right.preOrderSearch(no);
        }

        return null;
    }

    //中序遍历查找

    /**
     * @param no 查找的no
     * @return 如果找到就返回该Node，否则返回null
     */
    public HeroNode infixOrderSearch(int no) {
        if (this.left != null) {
            HeroNode leftheroNode = this.left.infixOrderSearch(no);
            if (leftheroNode != null) {
                return leftheroNode;
            }
        }

        System.out.println("中序遍历~~");
        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            return this.right.infixOrderSearch(no);
        }
        return null;
    }

    //后序遍历查找

    /**
     * @param no 查找的no
     * @return 如果找到就返回该Node，否则返回null
     */
    public HeroNode postOrderSearch(int no) {
        if (this.left != null) {
            HeroNode leftheroNode = this.left.postOrderSearch(no);
            if (leftheroNode != null) {
                return leftheroNode;
            }
        }

        if (this.right != null) {
            HeroNode rightheroNode = this.right.postOrderSearch(no);
            if (rightheroNode != null) {
                return rightheroNode;
            }
        }

        System.out.println("后序遍历~~");
        if (this.no == no) {
            return this;
        }

        return null;
    }
}