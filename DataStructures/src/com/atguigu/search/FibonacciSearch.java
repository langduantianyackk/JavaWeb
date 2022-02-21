package com.atguigu.search;

import java.util.Arrays;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: FibonacciSearch
 * @description: TODO
 * @date 2021/12/27 9:30
 **/
public class FibonacciSearch {
    private static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
//        int[] fib = fib();
//        System.out.println("fib="+ Arrays.toString(fib));
        int index = fibSearch(arr, 1234);
        if(index==-1){
            System.out.println("数组中不存在要查找的数");
        }else{
            System.out.println("arr="+index);
        }
    }

    //因为后面使用的mid = low + F(k-1) - 1,需要使用到斐波那契数列，所以想获取一个斐波那契数列
    //非递归方法得到斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1] + f[i-2];
        }

        return f;
    }

    //编写斐波那契查找算法
    //使用非递归方式
    public static int fibSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length-1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值(类似于二分查找算法中的mid，只不过mid的计算方式不同)
        //1，得到斐波那契数列
        int[] f = fib();//
        //循环得到将原数组扩容到斐波那契数长度的f[k]-1值
        //我的：
//        while(arr.length > f[k] - 1){
//            k+=1;
//        }
        //2,循环得到将原数组扩容到斐波那契数长度的f[k]值
        //老师：因为用high判定得到的扩容长度为f[k]-1,但是数组high = arr.length-1,所以原数组长度扩容长度为f[k]
        while(high > f[k] - 1){
            k+=1;
        }

        //3,因为f[k]的值可能小于arr的长度，所以使用Arrays类，创建新数组，并指向temp[]
        int[] temp = Arrays.copyOf(arr,f[k]);
        //4,将temp中扩容后的多出的位置的元素全部置为arr[high]
        for (int i = high +1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //5,使用while循环处理，查找key值
        while(low <= high){
            mid = low+f[k-1] - 1;
            if(key < temp[mid]){//查找的key可能mid的左侧在的这一段中，长度为f[k-1]
                high = mid - 1;//将mid左侧的所有数看成整体，需要在其中进行继续查找，索引它的high = mid - 1
                //1,因为F[k]=F[k-1]+F[k-2] =>（F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1成立
                //2,所以第一次长度(f[k]-1)的mid= low + f[k-1] - 1
                //3,那么第一次长度的mid左侧长度为f[k-1]-1个元素，继续拆分f[k-1]-1 = (f[k-2]-1) + (f[k-3]-1) + 1
                //4,所以此时第二次的mid= low + f[k-2] -1,
                //从两次的mid值可以看出第二次的k值(k-2)的值比第一次的k值(k-1)少1，所以k--
                k--;
            }else if(key > temp[mid]){//查找的key可能在mid的右侧在的这一段中，长度为f[k-2]
                low = mid + 1;//将mid右侧的所有数看成整体，需要在其中进行继续查找，索引它的low = mid + 1
                //1,因为F[k]=F[k-1]+F[k-2] =>（F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1成立
                //2,所以第一次长度(f[k]-1)的mid= low + f[k-1] - 1
                //3,那么第一次长度(F[k]-1)的mid右侧长度为f[k-2]-1个元素，继续拆分f[k-2]-1 = (f[k-3]-1) + (f[k-4]-1) + 1
                //4，那么第二次长度(f[k-2]-1)的mid = low + f[k-3] - 1
                //从两次的mid值可以看出第二次的k值(k-3)的值比第一次的k值(k-1)少2，所以k-=2
                k-=2;
            }else {
                if(mid <arr.length){//mid的位置在非扩容的位置
                    return  mid;
                }else {//mid的位置在扩容的位置
                    return arr.length -1;
                }
            }
        }

        return -1;
    }
}
