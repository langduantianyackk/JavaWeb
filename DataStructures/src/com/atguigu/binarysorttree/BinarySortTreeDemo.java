package com.atguigu.binarysorttree;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: BinarySortTreeDemo
 * @description: TODO
 * @date 2022/1/7 15:46
 **/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
//        int[] arr = {7, 3};
//        int[] arr = {10};
//        int[] arr = {7,10,8};
        int[] arr = {7,10,11};
        //创建二叉排序树对象
        BinarySortTree binarySortTree = new BinarySortTree();
        //添加结点，用for循环
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历
        System.out.println("删除前中序遍历：");
        binarySortTree.infixOrder();//1,2,3,5,7,9,10,12

        //测试删除叶子结点
//        binarySortTree.deleNode(2);
//        binarySortTree.deleNode(5);
//        binarySortTree.deleNode(9);
//        binarySortTree.deleNode(12);
        //测试删除一颗子树的结点
//        binarySortTree.deleNode(1);
        //测试删除两颗子树的结点
//        binarySortTree.deleNode(7);

        //删除全部结点
//        binarySortTree.deleNode(2);
//        binarySortTree.deleNode(5);
//        binarySortTree.deleNode(9);
//        binarySortTree.deleNode(12);
//        binarySortTree.deleNode(7);
//        binarySortTree.deleNode(3);
//        binarySortTree.deleNode(1);
//        binarySortTree.deleNode(10);
        binarySortTree.deleNode(7);
        binarySortTree.deleNode(10);
        binarySortTree.deleNode(11);
        System.out.println("删除后中序遍历：");
        binarySortTree.infixOrder();//1,3,5,7,9,10,12



    }
}

//创建二叉排序树
class BinarySortTree {
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
            if (root.left == null && root.right == null&& root.value == value) {
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
                if(parentNode == null){
                   //如果删除结点有左子树
                    if(targetNode.left!=null){
                        root = targetNode.left;
                    }else {
                        root = targetNode.right;
                    }
                }else {
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

        //不使用递归的方式
//        //辅助变量node
//        Node node = this;
//        while (true) {
//            if (value == node.value) {
//                break;
//            } else if (value < node.value) {
//                if (node.left == null) {
//                    return null;
//                }
//                node =  node.left;
//            } else {
//                if (node.right == null) {
//                    return null;
//                }
//                node =  node.right;
//            }
//        }
//        return node;
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
