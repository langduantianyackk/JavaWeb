package com.atguigu.kmp;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: ViolenceMatch
 * @description: TODO
 * @date 2022/1/17 16:30
 **/
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1= "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2="尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index="+index);
    }

    //暴力匹配算法实现
    /**
     *
     * @param str1 字符串
     * @param str2 子字符串
     * @return 返回str1的第一次匹配的索引位置
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;
        int i = 0;//表示s1的索引
        int j = 0;//表示s2的索引


        while (i<s1Len && j<s2Len ){//保证匹配时不越界
            if (s1[i] == s2[j]) {//匹配成功
                j++;//继续查看下一个字符是否匹配
                i++;//继续查看下一个字符是否匹配
            } else {//没有匹配成功
                i = i - (j - 1);//i回溯到每次匹配的第一次个位置的下一个位置
                j = 0;//重新开始从第一个字符进行匹配
            }
        }

        //当最后一次匹配成功之后i和j都加了1
        if(j == s2Len){//最后一个字符匹配了，结束,
            //s1的索引i
            return i-s2Len;
        }else {
            return -1;//循环结束没有匹配到
        }


    }
}
