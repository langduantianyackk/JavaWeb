package com.atguigu.kmp;

import java.util.*;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: KMPAlgorithm
 * @description: TODO
 * @date 2022/1/17 19:52
 **/
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1  = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext("ABCDABD");
        System.out.println("next="+ Arrays.toString(next));
        int index = kmpSearch1(str1, str2, next);
        System.out.println("index="+index);
    }
    //kmp搜索算法:老师方式
    /**
     *
     * @param str1 进行查找的字符串的原始字符串
     * @param str2 查找的字符串
     * @param next 部分匹配值表
     * @return
     */
    public static int kmpSearch1(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {

            //kmp算法核心
            //当str1.charAt(i) != str2.charAt(j)时i的位置不变，改变j的位置，它的值为最大公共前后缀的下一位
            //因为str1和str2的前0到j-1之间完全匹配，那么str2的0到j-1的前缀和str1中和str2匹配的字符串中的后缀是完全相同
            //所以将当前j的位置前移为前缀的后一个位置即next[j-1](表示0到j-1位的最大公共前后缀的长度，它的值等于前缀的后一个位置下标)
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                //j不好理解
                j = next[j - 1];//改变j的位置，它的值为最大公共前后缀的下一位
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;//
            }
        }

        return -1;
    }

    //kmp搜索算法:我的方式不太好
    /**
     *
     * @param str1 进行查找的字符串的原始字符串
     * @param str2 查找的字符串
     * @param next 部分匹配值表
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next){
        int i = 0;//指向str1下标
        int j = 0;//指向str2下标
        while (i<str1.length() && j < str2.length()){
            if(str1.charAt(i) == str2.charAt(j)){
                i++;
                j++;
            }else {
                if(j>0){
                    //移动位数 = 已匹配的字符数 - 对应的部分匹配值
                    //i应当移动到的下标
//                    i = i-j  + (j - next[j-1]) => i = i - next[j-1]
                    i = i - next[j-1];//kmp算法核心：获取i移动到下一个i的移动步数
                }else {
                    i++;
                }
                j = 0;
            }
        }

        //完全匹配
        if(j==str2.length()){
            return i-j;// i-1  - j + 1
        }else {//不匹配
            return -1;
        }

    }

    //获取一个字符串(子串)的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1，那么部分匹配值为0
        for (int i = 1, j=0; i <dest.length(); i++) {

            //dest.charAt(i)!=dest.charAt(j)时，并且保证j>0,需要从next[j-1]获取j
            //直到发现dest.charAt(i)==dest.charAt(j)成立才退出
            //kmp算法核心
            while (j>0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }


            //仅针对相同的情况
            //i表示next数组的下标，也表示dest字符串的下标
            //j表示计数(字符串匹配值)，即后面字符和前面字符重复了，j+1
            //可以理解为：有两个dest,一个为对比的dest1，下标i从1开始，和被对比的dest2
            // ，用dest1从i=1开始查找字符dest2 j=0的字符是否相同，如果相同，j++,此时即有一个相同的字符，记录到next[0] = 1
            //  就查找dest2 j=1，在判断是否有相同的字符
            //    dest1: ABCDAB
            //部分匹配值：
            //   dest2: ABCDAB
            //下面条件满足，部分匹配值+1
            if(dest.charAt(i)==dest.charAt(j)){
                //j表示计数(字符串匹配值)，即后面字符和前面字符重复了，j+1
                j++;
            }

            next[i] = j;

        }

        return next;
    }
}
