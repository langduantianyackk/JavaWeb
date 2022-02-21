package com.atguigu.java;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: StringTest
 * @description: TODO
 * @date 2021/11/9 15:50
 **/
public class StringTest {
    private static final String MESSAGE = "taobao";
    public static void main(String[] args) {
        /*
        1.常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
        2.只要其中一个是变量，结果就在堆中。
        3.如果拼接的结果调用intern()方法，返回值就在常量池中
        */
        String a = "tao" + "bao";
        String b = "tao";
        String c = "bao";
        System.out.println("MESSAGE="+MESSAGE.toString());
        System.out.println("a="+ a.toString());
        System.out.println(a == MESSAGE);

        System.out.println("(b + c)="+(b + c));
        System.out.println((b + c) == MESSAGE);
    }
}
