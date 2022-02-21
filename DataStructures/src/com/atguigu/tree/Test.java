package com.atguigu.tree;

import java.util.ArrayList;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: Test
 * @description: TODO
 * @date 2021/12/29 16:48
 **/
public class Test {
    public static void main(String[] args) {
        //以ArrayList为例，看一下底层数组如何进行扩容
        //ArrayList底层维护了数组Object[] elementData;
//        ArrayList arrayList = new ArrayList();
        String strByte = "10101000";
        System.out.println((byte)Integer.parseInt(strByte,2));//2指明当前数为二进制数,byte指明最高位为符号位

    }
}
