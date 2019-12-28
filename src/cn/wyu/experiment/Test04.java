package cn.wyu.experiment;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test04 {
    static ArrayList<String> arr = new ArrayList<>();        //保存运算符
    static ArrayList<Integer> records = new ArrayList<>();   //记录当前记录后的数值
    static int minCount = 1000;                              //初始化最小记录数
    static int nowCount;                                     //当前的变换次数
    static int n;
    static int m;
    public static void main(String[] args) {
        /*
        1.从input.txt文件中，取到数据
         */
        ArrayList<Integer> list = getDatas();
        n = list.get(0);
        m = list.get(1);
        /*
        2.深度优先遍历，寻找到最优解，若n>m，遍历左子树，若n<m，遍历右子树。
         */
        if(calculateCount(n)){
            StringBuffer buf = new StringBuffer();
            for (int i = arr.size()-1; i >=0; i--) {
                buf.append(arr.get(i));
            }
            String str = String.valueOf(buf);
            System.out.println("最小变换次数为："+str.length());
            System.out.println("变换序列为："+str);
        /*
        3.把结果保存到output.txt文件中
         */
            saveResult(str);
        }else{
            System.out.println("该数字有误！");
        }

    }

    private static void saveResult(String str) {
        File file = new File("F:\\intellij idea\\arithmetic\\src\\output4.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(str.length()+"\n");
            writer.write(String.valueOf(str));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static boolean calculateCount(int n) {
        //判断，该整数是否能转换
        if(test(n)){
            return false;
        }
        if(n==m){      //找到所要得的结果。
            if(minCount<nowCount){
                minCount = nowCount;
            }
            return true;
        }
        nowCount++;
        records.add(n);
        if(nowCount<minCount){
            if(n>m){
                //遍历左子树
                arr.add("g");
                calculateCount(g(n));
            }
            else {
                //遍历右子树
                arr.add("f");
                calculateCount(f(n));
            }
        }
        return true ;
    }

    private static boolean test(int n) {
        for (int i = 0; i < records.size(); i++) {
            if(records.get(i)==n) return true;
        }
        return false;
    }

    private static int f(int n) {
        return n*3;
    }

    private static int g(int n) {
        return n/2;
    }


    private static ArrayList<Integer> getDatas() {
        //获取文件输入数据
        File file = new File("F:\\intellij idea\\arithmetic\\src\\input4.txt");
        BufferedReader reader = null;
        String tempStr;
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((tempStr = reader.readLine()) != null) {
                String str[] = tempStr.split(" ");
                    int num1 = new Integer(str[0]);
                    int num2 = new Integer(str[1]);
                    list.add(num1);
                    list.add(num2);
            }
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }
}
