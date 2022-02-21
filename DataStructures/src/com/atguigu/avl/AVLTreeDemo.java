package com.atguigu.avl;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: AVLTreeDemo
 * @description: TODO
 * @date 2022/1/10 9:05
 **/
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};//通过单选转(左旋转)一次得到平衡二叉树
//        int[] arr = {10,12,8,9,7,6};//通过单选转(右旋转)一次得到平衡二叉树
        int[] arr = {10,11,7,6,8,9};//通过单选转(右旋转)一次得不到到平衡二叉树
//        int[] arr = {2,1,6,5,7,3};//通过单选转(左旋转)一次得不到到平衡二叉树
//                int[] arr = {4};//通过单选转(左旋转)一次得到平衡二叉树
        AVLTree avlTree = new AVLTree();
        //构建BST二叉排序树
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("二叉排序树：");
        avlTree.infixOrder();

        //在没有平衡处理之前，二叉排序树的高度
//        System.out.println("在平衡处理之前：");
//        System.out.println("树的高度=" + avlTree.getRoot().height());//4
//        System.out.println("左子树的高度=" + avlTree.getRoot().leftHeight());//1
//        System.out.println("右子树的高度=" + avlTree.getRoot().rightHeight());//3
        //平衡处理，二叉排序树的高度
        System.out.println("在平衡处理之后：");
        System.out.println("树的高度=" + avlTree.getRoot().height());//3
        System.out.println("左子树的高度=" + avlTree.getRoot().leftHeight());//2
        System.out.println("右子树的高度=" + avlTree.getRoot().rightHeight());//2
        System.out.println("当前根结点=" + avlTree.getRoot());//8

    }
}

//创建AVLTree
class AVLTree {
    //定义根结点
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除结点的父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除结点
    public void deleNode(int value) {
        if (root == null) {
            return;
        } else {
            //1,查找删除的结点
            Node targetNode = root.search(value);
            //如果没有找到删除的结点
            if (targetNode == null) {
                return;
            }

            //如果二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //2,结点超过一个，查找删除的结点的父结点
            Node parentNode = root.searchParent(value);//parentNode有可能为空
            //3,删除结点
            //3.1，删除叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //确定targetNode是父结点parentNode的左子结点还是右子结点
                if (parentNode.left == targetNode) {//左子结点
                    parentNode.left = null;
                } else if (parentNode.right == targetNode) {//右子结点
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//3.3删除两颗子树的结点
                //删除有两颗子树的结点，以下使用思路：
                //思路一:从targetNode的右子树中找到最小结点值，删除最小结点minNode，将targetNode.value = minNode.value
                //思路二:从targetNode的左子树中找到最大结点值，删除最大结点maxNode，将targetNode.value = maxNode.value
                //以下代码使用思路二实现
                int temp = 0;//存储最小结点的值
                Node minNode = null;//表示存储最小结点
                minNode = targetNode.right;//假设要删除结点的右子树就是最小结点
                //在targetNode的右子树中找到最小结点
                while (true) {//循环遍历查找最小结点
                    if (minNode.left != null) {//最小结点只能往targetNode的右子树的左边查找
                        minNode = minNode.left;
                    } else {
                        break;
                    }
                }

                //循环结束，此时minNode就是最小结点
                //用一个临时标量temp存储最小结点
                temp = minNode.value;

                //删除最小结点minNode对应的二叉排序树中的结点
                //1,查找minNode的父结点
                Node minParentNode = searchParent(minNode.value);
                //2.删除最小结点minNode
                if (minParentNode.left == minNode) {
                    minParentNode.left = null;
                } else if (minParentNode.right == minNode) {
                    minParentNode.right = null;
                }

                //将temp赋值给targetNode的value，使其成为最小结点
                targetNode.value = temp;
            } else {
                //3.2删除只有一颗子树结点
                //3.2.1并且删除的结点是根结点
                if (parentNode == null) {
                    //如果删除结点有左子树
                    if (targetNode.left != null) {
                        root = targetNode.left;
                    } else {
                        root = targetNode.right;
                    }
                } else {
                    //3.2.1并且删除的结点是不是根结点
                    if (targetNode.left != null) {//targetNode有左子树
                        //确定targetNode是父结点parentNode的左子结点还是右子结点
                        if (parentNode.left == targetNode) {//左子结点
                            parentNode.left = targetNode.left;
                        } else {//右子结点
                            parentNode.right = targetNode.left;
                        }
                    } else {//targetNode有右子树
                        //确定targetNode是父结点parentNode的左子结点还是右子结点
                        if (parentNode.left == targetNode) {//左子结点
                            parentNode.left = targetNode.right;
                        } else {//右子结点
                            parentNode.right = targetNode.right;
                        }
                    }
                }
            }

        }
    }

    //添加节点
    public void add(Node node) {
        //添加的是第一个结点
        if (root == null) {
            root = node;
        } else {
            //添加结点
            root.add(node);
        }
    }

    //中序遍历结点
    public void infixOrder() {
        if (root == null) {
            System.out.println("根结点为空，无法中序遍历");
        } else {
            //遍历二叉排序树
            root.infixOrder();
        }
    }
}

//创建结点
class Node {
    int value;
    Node left;//指向左子树
    Node right;//指向右子树

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //编写方法，返回当前结点的高度：以该结点为根结点的树的高度
    public int height() {
        //先求出左右子树的高度的最大值，在加1就是该结点的高度
        //递归计算左右子树高度，访问到
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate(){
        //1,创建新的结点，值为当前根结点的值
        Node newNode = new Node(this.value);
        //2,让新结点的左子树指向当前结点的左子树
        newNode.left = this.left;
        //3,让新结点的右子树指向当前结点的右子树的左子树
        newNode.right = this.right.left;
        //4,让当前结点的值替换为右子结点的值
        this.value = this.right.value;
        //5,让当前结点的右子树指向右子树的右子树
        this.right = this.right.right;
        //6,让当前结点的左子树指向新结点
        this.left = newNode;
    }

    //右旋转方法
    public void rightRotate(){
        //1,创建新结点，值为当前根结点的值
        Node newNode = new Node(this.value);
        //2,让新结点的右子树指向当前结点的右子树
        newNode.right = this.right;
        //3,让新结点的左子树指向当前结点的左子树的右子树
        newNode.left = this.left.right;
        //4,让当前结点的值替换为左子结点的值
        this.value = this.left.value;
        //5,让当前结点的左子树指向当前结点左子树的左子树
        this.left = this.left.left;
        //6,让当前结点的右子树指向新结点
        this.right = newNode;

    }


    //查找要删除的结点
    //使用递归
    /**
     * @param value 删除结点的value值
     * @return 如果找到，返回该结点，否则返回null
     */
    public Node search(int value) {
        //找到了，当前结点的值就是要查找的值
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//要查找的值小于当前结点的值
            if (this.left == null) {//当前结点的没有左子结点
                return null;//要查找的值不存在
            }
            //向左子树递归查找
            return this.left.search(value);
        } else {//要查找的值大于当前结点的值
            if (this.right == null) {//当前结点的没有右子结点
                return null;//要查找的值不存在
            }
            //向右子树递归查找
            return this.right.search(value);
        }
    }

    //查找要删除结点的父结点

    /**
     * @param value 要查找的结点的值
     * @return 返回找到要删除结点的父结点，否则返回null
     */
    public Node searchParent(int value) {
        //要查找的值不是根结点
        //如果当前结点是删除结点的父结点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);//向右子树递归查找
            } else {
                return null;//没找打要删除结点的父结点
            }
        }
    }

    //添加节点
    //递归的形式添加结点，需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            System.out.println("结点为空，添加失败");
            return;
        }

        //判断传入的结点的值和当前结点的关系
        if (node.value < this.value) {
            //如果当前结点的左子结点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {//当前结点的值大于等于传入结点
            //如果当前结点的右子结点为null
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
            }
        }

        //添加完一个结点后，判断：(右子结点的高度 - 左子结点高度) > 1，那么就左旋转
        if(rightHeight() - leftHeight() > 1){
            //如果当前结点的右子树的左子结点的高度大于右子结点的高度，那么当前结点的右子树右旋转
            if(right!=null && (right.leftHeight() > left.rightHeight())){
                //当前结点的右子树右旋转
                right.rightRotate();
            }
            //当前结点左旋转
            this.leftRotate();
        }else if(leftHeight() - rightHeight() > 1){//添加完一个结点后，判断:(左子结点的高度 - 右子结点的高度) > 1,那么右旋转

            //如果当前结点的左子树的右子结点的高度大于左子结点的高度，那么当前结点的左子树左旋转
            if(left!=null && (left.rightHeight() > left.leftHeight())){
                //当前结点的左子树左旋转
                left.leftRotate();
            }
            //当前结点右旋转
            this.rightRotate();
        }
    }

    //中序遍历
    public void infixOrder() {
        //向左子树递归
        if (this.left != null) {
            this.left.infixOrder();
        }

        //输出对当前结点
        System.out.println(this);

        //向右子树递归
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}