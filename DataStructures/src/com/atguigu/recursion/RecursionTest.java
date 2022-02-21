package com.atguigu.recursion;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: TestDemo1
 * @description: TODO
 * @date 2021/12/15 16:33
 **/
public class RecursionTest {
    public static void main(String[] args) {
        test(5);
        int factorial = factorial(5);
        System.out.println(factorial);
    }

    //输出什么？
    public static void test(int n){
        if(n>2){
            test(n-1);
        }
        System.out.println("n=" + n);
    }

    //阶乘
    public static int factorial(int n){
        if(n == 1){
            return  1;
        }else {
           return n*factorial(n-1);
        }
    }
}
