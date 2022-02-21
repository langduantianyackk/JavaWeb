package com.atguigu.greedy;

import java.util.*;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: GreedyAlgorithm
 * @description: TODO
 * @date 2022/1/21 10:07
 **/
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //1，创建电台集合，使用map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
//        HashSet<String> hashSet1 = new HashSet<>();
//        hashSet1.add("北京");;
//        HashSet<String> hashSet2 = new HashSet<>();
//        hashSet2.add("北京");
//        hashSet2.add("深圳");
//        HashSet<String> hashSet3 = new HashSet<>();
//        hashSet3.add("北京");
//        hashSet3.add("深圳");
//        hashSet3.add("上海");
//        HashSet<String> hashSet4 = new HashSet<>();
//        hashSet4.add("北京");
//        hashSet4.add("深圳");
//        hashSet4.add("上海");
//        hashSet4.add("成都");
//        HashSet<String> hashSet5 = new HashSet<>();
//        hashSet5.add("杭州");
//        hashSet5.add("大连");
        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);

        //2，创建allAreas集合，存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        //将broadcasts中的所有value取出(取得的是Collection集合，它的元素为类型HashSet<String>)放入allAreas中
        for (HashSet<String> set: broadcasts.values()){
            allAreas.addAll(set);
        }
        System.out.println(allAreas);

        //4，创建ArrayList集合存放电台
        ArrayList<String> selects = new ArrayList<>();

        //我的作法
//        while (allAreas.size()>0){
//            //3,计算broadcasts中value值中未被覆盖区域的值
//            int maxNum = 0;
//            String maxKey = "";
//            //循环取得broadcasts的key值
//            for (String key :broadcasts.keySet()){
//                int num = 0;//保存key对应的地区中未被覆盖的地区
//                //通过key取得并且遍历value
//                for(String str:broadcasts.get(key)){
//                    if(allAreas.contains(str)){//判断未被覆盖的所有地区中是否包含当前key对应的地区
//                        num++;//未被覆盖地区加1
//                    }
//                }
//
//                //通过循环和maxNum比较得到maxKey
//                if(maxNum < num){
//                    maxNum = num;//保存当前的num
//                    maxKey = key;//保存当前的key
//                }
//            }
//
//            //4，将maxKey放入到ArrayList
//            selects.add(maxKey);
//
//            //5,下一次查找电台时应该去掉已经覆盖掉的地区
//            allAreas.removeAll(broadcasts.get(maxKey));
//            System.out.println(allAreas);
//        }
//
//        System.out.println(selects);


        //老师作法
        HashSet<String> tempSet = new HashSet<>();//表示电台(k1...k5)对应的能够覆盖的地区和未覆盖的地区allAreas的交集
        String maxKey = null;//表示电台中最多未覆盖地区的电台
        while(allAreas.size()!=0){
            maxKey = null;
            //循环取得broadcasts的key
            for (String key:broadcasts.keySet()){
                //每次往tempSet中存储数据前要把之前的数据清空
                tempSet.clear();
                //通过key取得能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                //将areas加入到tempSet中
                tempSet.addAll(areas);
                //求交集
                // tempSet.retainAll(allAreas):求得tempSet和allAreas的交集并且赋给tempSet
                tempSet.retainAll(allAreas);
                if(tempSet.size() > 0 &&(maxKey==null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }

            if(maxKey!=null){
                selects.add(maxKey);//将电台对应的最多未覆盖地区的电台的maxkey加入到selects
                allAreas.removeAll(broadcasts.get(maxKey));//下一次循环是，去除已经选好的电台对应的地区
            }
        }

        System.out.println(selects);
    }
}
