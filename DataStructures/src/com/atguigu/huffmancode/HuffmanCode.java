package com.atguigu.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: HuffmanCode
 * @description: TODO
 * @date 2022/1/3 19:44
 **/
public class HuffmanCode {
    public static void main(String[] args) {
//        //2)得到"i like like like java do you like a java "对应的byte[]数组
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);//40
//
//        //3,将contentBytes中的data，以及data出现次数存入到nodes中的每个Node(data,weight)中
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes="+nodes);
//
//        //4,通过nodes创建赫夫曼树
//        System.out.println("赫夫曼树：");
//        Node huffmanTree = createHuffmanTree(nodes);//huffmanTree为赫夫曼树的根结点
//        //5,测试赫夫曼树是否创建成功，使用前序遍历
//        System.out.println("前序遍历赫夫曼树:");
//        huffmanTree.preOrder();
//
//        //6,通过赫夫曼树得到赫夫曼编码表
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
//        System.out.println("~生成的赫夫曼编码表"+huffmanCodes);
//
//        //7,通过原始byte[]数组和赫夫曼编码表得到编码后的byte[]数组
//        byte[] bytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffmanCodesBytes=" + Arrays.toString(bytes));//17

        //一，数据的编码
        //发送huffmanCodes编码过后的bytes
        //将以上步骤封装到方法中
        //将content压缩
//        String content = "i like like like java do you like a java";
//        String content = "hello world";
//        byte[] contentBytes = content.getBytes();//原始长度：40
//        byte[] huffmanCodesBytes = huffmanZip(content.getBytes());
//        System.out.println("huffmanCodesBytes=" + Arrays.toString(huffmanCodesBytes) + "，赫夫曼编码后长度=" + huffmanCodesBytes.length);//17
//
//        //测试decode
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
//        System.out.println("原始数组为="+new String(sourceBytes));

        //测试压缩文件
//        String srcFile = "E:\\dir1\\maobuyi.jpg";
//        String desFile = "E:\\dir1\\maobuyi.zip";
//        zipFile(srcFile, desFile);
//        System.out.println("压缩成功");

        //测试解压文件
        String srcFile = "E:\\dir1\\maobuyi.zip";
        String desFile = "E:\\dir1\\maobuyi2.jpg";
        unZipFile(srcFile,desFile);
        System.out.println("解压成功");

    }

    //完成数据的解压
    //思路：
    // 1，huffmanCodesBytes=[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //    重新转换成二进制的字符串"101010011011110111..."
    //2,赫夫曼编码对应的二进制字符串"101010011011110111..." ==>对照赫夫曼编码表 ==> "i like like like java do you like a java"


    //1,编写方法，完成对文件的压缩
    private static void zipFile(String srcFile, String desFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //文件输入流
            fis = new FileInputStream(srcFile);

            //文件输出流
            fos = new FileOutputStream(desFile);

            //与文件有关的对象输出流
            oos = new ObjectOutputStream(fos);

            //创建一个与源文件大小的byte[]
            byte[] b = new byte[fis.available()];

            //读取文件到b
            fis.read(b);

            //调用赫夫曼压缩，得到压缩后的huffmanCodesBytes
            byte[] huffmanCodesBytes = huffmanZip(b);

            //将赫夫曼编码后的byte以对象方式写入文件到输出流
            oos.writeObject(huffmanCodesBytes);
            //将赫夫曼编码表以对象方式写入文件到输出流
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //2,编写方法，完成对压缩数据的解压
    private static void unZipFile(String srcFile, String desFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectInputStream ois = null;
        try {
            //文件输入流
            fis = new FileInputStream(srcFile);

            //文件输出流
            fos = new FileOutputStream(desFile);

            //与文件有关的ObjectInputStream
            ois = new ObjectInputStream(fis);

            //将源文件中的读入到huffmanCodesBytes
            byte[] huffmanCodesBytes = (byte[]) ois.readObject();

            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>) ois.readObject();

            //将huffmanCodesBytes进行解压
            byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);

            //将sourceBytes写出到文件中
            fos.write(sourceBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 解压赫夫曼编码后的byte[]数组
     *
     * @param huffmanCodes     赫夫曼编码表
     * @param huffmanCodeBytes 通过赫夫曼编码表压缩后的byte[]数组
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanCodeBytes) {
        //1,将huffmanCodeBytes转换成String
        StringBuilder stringBuilder = new StringBuilder();
        //遍历huffmanCodeBytes取得byte转换成二进制字符串
        for (int i = 0; i < huffmanCodeBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanCodeBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanCodeBytes[i]));
        }

        //2,把字符串按照指定的赫夫曼解码表进行解码
        //赫夫曼解码表：把赫夫曼编码表进行调换，因为是反向查询 之前是a -> 100 现在是100 -> a
        Map<String, Byte> huffmanCodesReverse = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            huffmanCodesReverse.put(entry.getValue(), entry.getKey());
        }

        //3,创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//每次读取到value之后，下一次开始读取stringBuilder之前重置count=1
            Byte b = null;//接收通过key读取的value值，默认为null
            while (true) {
                String strByte = stringBuilder.substring(i, i + count);//从stringBuilder中读取指定长度的字符串
                b = huffmanCodesReverse.get(strByte);//以字符串为key读取value
                if (b == null) {//没读到value
                    count++;
                } else {//读到value
                    break;//只要读到了一个byte数就结束循环
                }
            }

            list.add(b);//将读到的byte添加到list中
            i += count;//下一次开始读取点为i = i+count;
        }
        //当for循环结束，还要将list转换成byte[]
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }

        return bytes;
    }

    /**
     * 将一个byte 转成 一个二进制字符串
     *
     * @param flag 表示是否需要补高位，如果是true，表示需要补高位
     * @param b
     * @return 是字节b对应的二进制字符串(注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //1,
        //使用变量保存b
        int temp = b;//强转

        if (flag) {
            //如果是正数还需要补高位
            temp |= 256;//按位或256   1 0000 0000 | 0000 0001  => 1 0000 0001
        }

        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
//            System.out.println("str=" + str);
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //压缩过程(编码)：将上面主要步骤分装起来，便于调用
    private static byte[] huffmanZip(byte[] bytes) {
        //1,将bytes中的data，以及data出现次数存入到nodes中的每个Node(data,weight)中
        List<Node> nodes = getNodes(bytes);
        //2,通过nodes创建赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);//huffmanTree为赫夫曼树的根结点
        //3,通过赫夫曼树得到赫夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        //4,通过原始byte[]数组和赫夫曼编码表得到编码后的byte[]数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;//返回huffmanCodes编码过后的huffmanCodeBytes
    }

    //编写方法，将字符串对应的byte[]数组，通过赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
    /**
     * @param bytes        原始字符串对应的byte[]数组
     * @param huffmanCodes 赫夫曼编码表
     * @return 返回处理后的byte[]数组
     * 举例：i like like like java do you like a java => byte[] contentBytes = content.getBytes();
     * 返回的是：10101001101111011110100110111101111010011011110111101000011000011100110011110000110
     * 01111000100100100110111101111011100100001100001110 对应的byte[] huffmanCodeBytes
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1，拼接读取到的byte数放入到stringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        //遍历数组
        for (byte item : bytes) {
            String s = huffmanCodes.get(item);
            stringBuilder.append(s);//拼接每次通过数组得到的key所对应value
        }

        //得到stringBuilder是长度133，原来40
        //所以要将stringBuilder=>二进制数组格式(每8位变成一位二进制数)
        //2，统计返回byte[] huffmanCodeBytes 长度len
        //简洁写法：int len = (stringBuilder.length + 7)/8
        int len;//压缩后byte[]数组的长度
        if (stringBuilder.length() % 8 == 0) {
            //转换成byte[]数组的长度为
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //3,创建压缩后的byte[]数组
        byte[] huffmanCodeBytes = new byte[len];

        //4,将stringBuilder转换长byte[]数组
        int index = 0;//记录第几个byte
        //遍历stringBuilder，每8为转换成一位byte数
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将而二进制数strByte转换成byte进制存入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;//返回赫夫曼编码后的byte[]
    }

    //为了调用方便，重载getCodes,得到赫夫曼编码表
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }

        //递归处理
        //处理root左子树，得到赫夫曼编码
        getCodes(root.left, "0", stringBuilder);
        //处理root左子树，得到赫夫曼编吗
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路：
    //1,将赫夫曼编码表存放在Map<Byte,String> 形式 32->01 97->100 100->11000...
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2,在生成赫夫曼编码时，需要拼接路径，定义一个StringBuilder来存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能：得到以传入的node结点的所有叶子结点的赫夫曼编码，并放入到huffmanCodes集合中
     *
     * @param node          传入结点(赫夫曼树的根结点)
     * @param code          路径：左子结点0，右子结点1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//node结点为空，什么也不做
            if (node.data == null) {//说明此时node还没有访问到叶子结点
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//此时找到叶子结点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }


//    //前序遍历
//    private static void preOrder(Node root) {
//        if (root != null) {
//            root.preOrder();
//        } else {
//            System.out.println("赫夫曼树为空，无法遍历");
//            return;
//        }
//    }

    //3)编写一个方法，将准备构建赫夫曼树的node，放入到list中，
    // 形式如[Node[date=97,weight=5],Node[date=32,weight=9],...],
    // 体现d:1 y:1 u:1 j:2 v:2 o:2 l:4 k:4 e:4 i:5 a:5 :9

    /**
     * @param bytes 接收字节数组
     * @return 返回List，形式Node[date=97,weight=5],Node[date=32,weight=9],...]
     */
    private static List<Node> getNodes(byte[] bytes) {
        //1,创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //2,遍历bytes，统计存储每个byte出现的次数 -> map
        Map<Byte, Integer> counts = new HashMap<>();
        //3,循环遍历bytes，统计各个byte数的次数，放入到map
        for (byte b : bytes) {
            Integer count = counts.get(b);//取得map中key(存放数据)对应的次数
            if (count == null) {//Map还没有这个数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);//取得次数+1
            }
        }

        //4,将map中的字符及次数依次添加到ArrayList中
        //遍历map
        for (Map.Entry<Byte, Integer> entrySet : counts.entrySet()) {
            nodes.add(new Node(entrySet.getKey(), entrySet.getValue()));//每次将Node(data,weight)结点添加到nodes中
        }

        return nodes;
    }

    //通过list构建赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //1,从小到大排序
            Collections.sort(nodes);

            //2,取出权值最小的连个结点构建新二叉树
            //2.1取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //2.2取出第二颗第二小二叉树
            Node rightNode = nodes.get(1);
            //2.3创建新的二叉树，它的根结点，没有data，只有权值
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            //2.4将leftNode rightNode parentNode构建成二叉树
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            //3,从nodes中删除已经构建完的两个结点leftNode,rightNode
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //4,将新构建的父结点加入到nodes中
            nodes.add(parentNode);
        }

        //5,返回nodes的结点,此时nodes中只有一个元素，为赫夫曼树的根结点
        return nodes.get(0);
    }
}

//1）创建Node{data{存放数据},weight(权值),left,right}
class Node implements Comparable<Node> {
    Byte data;//存放数据(字符)，比如'a' => 97
    int weight;//权值(字符出现次数)
    Node left;//指向左子树
    Node right;//指向右子树

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);//从小到大排序
    }

    //前序遍历
    public void preOrder() {
        //输出当前结点
        System.out.println(this);

        //向左递归
        if (this.left != null) {
            this.left.preOrder();
        }

        //向右递归
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
