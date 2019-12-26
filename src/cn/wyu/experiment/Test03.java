package cn.wyu.experiment;

import java.io.*;
import java.util.*;

public class Test03 {
    public static void main(String[] args) {

        /*
        1.从文件中读取数据
         */
        int[][] array = getDatas();

        /*
        2.
         */
        int count = getMaxNum(array, array.length);
        System.out.println("最小会场数为："+count);

        /*
        3.把结果输出到input3文件中。
         */
        saveMinNum(count);
    }

    private static void saveMinNum(int m){
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("F:\\intellij idea\\arithmetic\\src\\output3.txt"));
            dataOutputStream.write(m);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int getMaxNum(int[][] array, int length) {
        int n = length;
        int count = 0;   //安排活动的场数
        int temp = 0;    //比较时间
        while (n > 0) {
            for (int i = 0; i < length; i++) {
                //若该活动的开始时间，大于上一次活动的结束时间，并且玩没有被安排
                if(array[i][0]>temp && array[i][2]==0){
                    //当前结束时间，为下一次开始时间，做比较。
                    temp = array[i][1];
                    //当前活动已被安排
                    array[i][2] = 1;
                    //没被安排的活动-1
                    n--;
                }
            }
            count++;
            temp = 0;     //重新遍历。
        }
        return count;
    }

    private static int[][] getDatas() {
        //获取文件输入数据
        File file = new File("F:\\intellij idea\\arithmetic\\src\\input3.txt");
        BufferedReader reader = null;
        String tempStr;
        // ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[][] array = null;
        int i = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((tempStr = reader.readLine()) != null) {
                String str[] = tempStr.split(" ");
                if (str.length == 1) {
                    int num = new Integer(str[0]);
                    array = new int[num][3];
                } else {
                    int num1 = new Integer(str[0]);
                    int num2 = new Integer(str[1]);
                    array[i][0] = num1;
                    array[i][1] = num2;
                    array[i][2] = 0;
                    i++;
                }

                //System.out.println(Arrays.toString(str));
            }
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }


        return array;
    }
}
