package com.atguigu.stack;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: Calculator
 * @description: TODO00
 * @date 2021/12/14 11:02
 **/
public class Calculator {
    public static void main(String[] args) {
        //根据思路，完成表达式的运算
        String expression = "3+20*6-2*6/4-5";
//        String expression = "3+2*6-2*6/4-5*2+8-6*2/3-7";
//        String expression = "30+2*6-20";
        //1,创建两个栈：数栈numStack:存放数字  符号栈operStack存放符号
        ArrayStack2 numStack = new ArrayStack2(expression.length());
        ArrayStack2 operStack = new ArrayStack2(expression.length());

        //2,定义相关变量
        int index = 0;//实现扫描的字符串的位置
        int num1 = 0;//两数运算的第一个值
        int num2 = 0;//两数运算的第二个值
        int oper = 0;//两数运算的符号
        int res = 0;//两数运算的结果
        char ch = ' ';//将每次扫描到的字符保存到ch中
        String keepNum = "";//用于拼接多位数

        //3,开始用while循环扫描字符串
        while (true){
            //当扫描到字符串的末尾的下一个位置，结束循环
            if(index == expression.length()){
                break;
            }

            ch = expression.charAt(index);

            //判断是否是一个符号
            if(operStack.isOper(ch)){
                if(operStack.isEmpty()){//如果符号栈为空，就直接入栈
                    operStack.push(ch);
                }else{
                    int chPriority = operStack.priority(ch);
                    int[] stack = operStack.getStack();
                    for (int i = operStack.getTop(); i >= 0; i--) {
                        //如果当前的符号的优先级小于或者等于符号栈中的符号的优先级
                        //那么，就需要从数栈中pop出两个数，从符号栈中pop出一个符号进行运算，
                        // 将运算的结果存入符号栈中
                        if(chPriority <= operStack.priority(stack[i])){
                            //从数栈中pop出两个数，从符号栈中pop出一个符号进行运算
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            res = numStack.cal(num1, num2, oper);
                            //将运算的结果存入符号栈中
                            numStack.push(res);
                        }else{//如果当前的符号的优先级大于符号栈中符号的优先级，那么直接入符号栈
                            //1,此时入栈时，符号栈不为空，当前的符号的优先级大于符号栈中符号的优先级，直接入力
                            operStack.push(ch);
                        }
                    }

                    //2,当for循环完了之后，符号栈为空,则将当前符号加入到符号栈中
                    if(operStack.isEmpty()){//如果符号栈为空，就直接入栈
                        operStack.push(ch);
                    }
                }
                index++;
            }else{
                //该符号是数字的情况
                // numStack.push(ch-48);//ch的位数是一位数字
                //1，当处理多位数时，ch就不能直接入数栈
                //2，在处理数时，需要expression的表达式的index后再看一位，如果是数，继续扫描，如果是符号，则前面扫描到的组合的数入栈
                //3,因此定义字符串变量用于拼接
                 keepNum = keepNum + ch;//用于拼接扫描到的数字
                while(true){
                    index ++;
                    //如果此时index超出了最后一位,那么就把拼接完的数字直接入栈，并break
                    if(index == expression.length()){
                        numStack.push(Integer.parseInt(keepNum));
                        break;
                    }
                    char temp = expression.charAt(index);
                    if(operStack.isOper(temp)){
                        numStack.push(Integer.parseInt(keepNum));
                        break;
                    }
                    keepNum = keepNum + temp;
                }

                keepNum = "";//每次循环结束清空keepNum，用于拼接下一次扫描到的数字
            }
        }

        //通过上面的循环，所有的字符经过计算都放入到数栈和符号栈中
        //然后数栈pop两个数字，符号栈pop一个符号，运算结果放入数栈中，
        // 通过循环(循环条件为符号栈中符号个数)最终使得数栈中数字个数为1，符号栈为空，则此时在数栈中的数字就是表达式结算之后的之后
        for (int i = operStack.getTop(); i >= 0; i--) {
            //从数栈中pop出两个数，从符号栈中pop出一个符号进行运算
            num1 = numStack.pop();//先出栈的为运算左侧的值
            num2 = numStack.pop();//后出栈的为运算右侧的值
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //将运算的结果存入符号栈中
            numStack.push(res);
        }

        //此时数栈中只有一个数字，输出它
        int num = numStack.pop();
        System.out.printf("表达式计算的结果为：%s=%d",expression,num);
    }
}
//创建一个栈，使用前面创建好的
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//使用数组模拟栈,数据就放在数组中
    private int top = -1;//定义一个变量top表示栈顶，默认-1

    public int getTop() {
        return top;
    }

    //构造器初始化栈
    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new  int[maxSize];
    }

    public int[] getStack() {
        return stack;
    }

    //站满
    public boolean isFull(){
        //判断站满条件
//        if(top==maxSize-1){
//            return true;
//        }
//        return false;
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        //判断栈空
//        if(top == -1){
//            return true;
//        }
//        return false;
        return top == -1;
    }

    //入栈
    public void push(int value){
        //判读是否栈满
        if(isFull()){
            System.out.printf("栈已满,%d无法入栈\n",value);
            return;
        }
        //入栈操作
//        top++;
//        stack[top] = value;
        stack[++top] = value;
    }

    //出栈
    public int pop(){
        //判断是否栈空
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法出栈");
        }
        return stack[top--];
    }

    //遍历栈,遍历时需要从栈顶遍历到栈低
    public void show(){
        //判断是否栈空
        if(isEmpty()){
            System.out.println("栈为空!");
            return;
        }
        //遍历栈
        for(int i=top; i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //扩展功能
    //判断符号的优先级,优先级有程序员定义，优先级使用数字，数字越大，优先级越高
    public int priority(int oper){
        if(oper =='*' || oper == '/'){
            return 1;
        }else if(oper =='+' || oper == '-'){
            return 0;
        }else{
            return -1;//假设目前表达式中只有 + - * /
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val =='-' || val =='*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0;//res 用于存放计算结果
        switch(oper){
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
