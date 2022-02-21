package com.atguigu.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: PolandNotation
 * @description: TODO
 * @date 2021/12/15 15:29
 **/
public class PolandNotation {
    public static void main(String[] args) {
//        //定义一个逆波兰表达式
//        //表达式(30+4)*5-6逆波兰表达式为30 4 + 5 * 6 -
//        //表达式:4*5 - 8 + 60 + 8 / 2逆波兰表达式为4 5 * 8 - 60 + 8 2 / +
//        //说明：为了方便。逆波兰表达式3用数字和符号使用空格隔开
////        String suffixExpression = "30 4 + 5 * 6 -";
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//76
//        //1,先将"3 4 + 5 x 6 -"=>放到ArrayList中
//        //2,将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
//        List<String> list = PolandNotation.getListString(suffixExpression);//将字符串转化到list中，对list配合栈进行遍历
//        System.out.println(list);
//
//        //计算逆波兰表达式
//        int res = calculate(list);
//
//        //输出结果
//        System.out.printf("逆波兰表达式的计算结果为%s=%d",suffixExpression,res);


        //一，完成一个中缀表达式转换成后缀表达式的功能
        //1,“1+((2+3)×4)-5" => "1 2 3 + 4 × + 5 –"
        //2,对一个字符串直接扫描比较慢，所以讲中缀表达式转换成List<String>,再转成后缀表达式的List<String>
        //即“1+((2+3)×4)-5" => ArrayList[1,+,(,(,2,+,3,),×,4,),-,5]
        //1,中缀表达式
        String expression = "1+((2+3)*4)-5";
        //2,将中缀表达式转换成list
        List<String> toInfixExpressionList = toInfixExpressionList(expression);
        System.out.println(toInfixExpressionList);
        //3,将list转换后缀表达式操作
        List<String> suffixExpressionList = parseSuffixExpressionList(toInfixExpressionList);
        System.out.println(suffixExpressionList);

        //二，后缀表达式的计算
        int calculate = calculate(suffixExpressionList);
        System.out.println(calculate);

    }

    /**
     * 功能：将中缀表达式转换成List<String>
     *
     * @param s 中缀表达式字符串
     * @return List<String>
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义list<String>用于接收转换后的结果
        List<String> ls = new ArrayList<>();
        //定义i用于扫描字符串expression
        int i = 0;
        //定义字符c接收每次取得的结果
        char c;
        //定义字符串str用于拼接使用
        String str;
        do {
            //如果扫描到的字符是非数字
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(c + "");
                i++;
            } else {//如果是数字，考虑多位数
                str = "";//用于拼接字符串
                //循环判断多位数
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                //将多位数加入到ls中
                ls.add(str);
            }
        } while (i < s.length());

        //返回得到的List<String>
        return ls;

    }

    /**
     * 功能;将list转换成后缀表达式
     *
     * @param ls 中缀表达式转换后的list
     * @return 后缀表达式
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //1,初始化两个栈，运算符栈s1,数栈s2
        //对数栈进行分析可知，对数栈的操作只有入栈，并且还要输出数栈的逆序为后缀表达式，
        //不妨用List代替数栈，输出的List就是后缀表达式
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        //2,从左到右扫描中缀表达式
        for (String item : ls) {
            //如果是一个，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {//如果是左括号"(",直接压入s1
                s1.push(item);
            } else if (")".equals(item)) {//否则，如果是右括号
                //循环将s1的栈顶运算符加入s2,直到遇到左括号"("位置
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                //丢弃左括号,重要重要重要
                s1.pop();
            } else {
                //循环判断如果item的优先级小于等于s1栈顶运算符的优先级,那么将s1栈顶的运算符加入到s2
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item压入到到s1
                s1.push(item);
            }
        }

        //将s1中剩余的运算符加入到s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }

        return s2;
    }

    /*
      例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
          1．从左至右扫描，将 3 和 4 压入堆栈；
          2．遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
          3．将 5 入栈；
          4．接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
          5．将 6 入栈；
          6．最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
       */
    public static int calculate(List<String> list) {
        //创建,将读到的数字放入到栈中
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //每次循环到的元素，如果是是数字，如果是则入栈
            //使用正则表达式来判断是否是一个数字
            //\\d+  \d表示匹配一位数字 \d+表示匹配多为数字  前面的\表示转义字符
            if (item.matches("\\d+")) {//匹配的是多位数
                //直接入栈
                stack.push(item);
            } else {
                //不是数字，则栈中pop出栈顶和次栈顶元素运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(item)) {
                    res = num1 + num2;
                } else if ("-".equals(item)) {
                    res = num1 - num2;
                } else if ("*".equals(item)) {
                    res = num1 * num2;
                } else if ("/".equals(item)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符号错误");
                }

                //运算结果再入栈
                stack.push(String.valueOf(res));
            }
        }

        //最后在stack中的数据就是表达式运算的最终结果
        return Integer.parseInt(stack.pop());

    }
    //完成逆波兰表达式的运算

    //讲一个逆波兰表达式中的数据和运算符依次放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分隔(以空格的方式分隔)
        String[] spilt = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        list = Arrays.asList(spilt);//将数组的元素添加到list中，目的为了对list进行遍历
        return list;
    }
}

//编写一个类Operation
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //功能：计算传入运算符的优先级
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result =  MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("传入的运算符有误");
                break;
        }

        return result;
    }
}
