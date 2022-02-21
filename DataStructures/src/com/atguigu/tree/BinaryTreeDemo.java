package com.atguigu.tree;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: BinaryTreeDemo
 * @description: TODO
 * @date 2021/12/30 9:15
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();

        //创建需要的结点信息
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明，先手动创建二叉树，后面学习递归创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);//设置根结点

//        //测试遍历二叉树
//        System.out.println("前序遍历:");
//        binaryTree.preOrder();//1,2,3,5,4
//        System.out.println("中序遍历:");
//        binaryTree.infixOrder();//2,1,5,3,4
//        System.out.println("后序遍历:");
//        binaryTree.postOrder();//2,5,4,3,1
//
//        //测试二叉树遍历查找
//        System.out.println("前序遍历查找:");
//        //前序遍历次数：4
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if(resNode!=null){
//            System.out.printf("找到了，信息为no=%d, name=%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("在二叉树中没找到结点no=%d的结点",5);
//        }
//
//        System.out.println("中序遍历查找:");
//        //中序遍历次数：3
//        resNode = binaryTree.infixOrderSearch(5);
//        if(resNode!=null){
//            System.out.printf("找到了，信息为no=%d, name=%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("在二叉树中没找到结点no=%d的结点",5);
//        }
//
//        System.out.println("后序遍历查找:");
//        //后序遍历次数：2
//        resNode = binaryTree.postOrderSearch(5);
//        if(resNode!=null){
//            System.out.printf("找到了，信息为no=%d, name=%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("在二叉树中没找到结点no=%d的结点",5);
//        }

        //测试删除结点
        System.out.println("删除前中序遍历：");
        binaryTree.infixOrder();
        binaryTree.delNode(2);
        System.out.println("删除后中序遍历：");
        binaryTree.infixOrder();

    }
}

//定义BinnaryTree  二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public void delNode(int no){
        //2,如果当前结点的左子结点不为空，并且左子结点就是要删除的点，
        // 那么this.left == null,并且就返回（结束递归删除）
        if(this.left!=null && this.left.no == no){
            this.left = null;
            return;
        }

        //3,如果当前结点的右子结点不为空，并且右子结点就是要删除的点，
        //那么this.right == null,并且就返回（结束递归删除）
        if(this.right!=null && this.right.no == no){
            this.right = null;
            return;
        }

        //4,如果第2步和第3步没有删除结点，那么就向左子树进行递归删除
        if(this.left!=null){
            this.left.delNode(no);
        }
        //5,如果第4步也没有删除结点，那么就向右子树进行递归删除
        if(this.right!=null){
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
     *
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
     *
     * @param no 查找的no
     * @return 如果找到就返回该Node，否则返回null
     */
    public HeroNode infixOrderSearch(int no) {
        if (this.left != null) {
            HeroNode leftheroNode = this.left.infixOrderSearch(no);
            if(leftheroNode!=null){
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
     *
     * @param no 查找的no
     * @return 如果找到就返回该Node，否则返回null
     */
    public HeroNode postOrderSearch(int no) {
        if (this.left != null) {
            HeroNode leftheroNode = this.left.postOrderSearch(no);
            if(leftheroNode!=null){
                return leftheroNode;
            }
        }

        if (this.right != null) {
            HeroNode rightheroNode = this.right.postOrderSearch(no);
            if(rightheroNode!=null){
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
