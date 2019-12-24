package cn.wyu.experiment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.从input2.txt文件中读取字符串
 * 2.确定当i或j等于零的情况
 * 3.判断i、j字符是否相等
 * 4.填表
 */
public class Test02 {
    public static void main(String[] args) {
        /*
        1.读取字符串
         */
        List<String> list = null;
        list = getString();
        String str1 = list.get(0);
        String str2 = list.get(1);

        /*
        2.创建一个二维数组，用于记录所得到的编辑距离的情况。
         */
        int editDistance = getEditDistance(str1,str2);
        System.out.println("编辑距离为："+editDistance);

        /*
        3.把结果输出到output2.txt文件中
         */
        saveResult(editDistance);
    }

    private static void saveResult(int editDistance) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("F:\\intellij idea\\arithmetic\\src\\output2.txt"));
            dataOutputStream.write(editDistance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getEditDistance(String str1,String str2) {
        //确定数组的大小
        int n = str1.length()+1;
        int m = str2.length()+1;
        //创建一个二维数组，用于记录编辑距离的情况
        int[][] d = new int[n][m];
        //当i=0,j>0
        for (int j = 0; j <m; j++) {
            d[0][j] = j;
        }
        //当i>0,j=0
        for (int i = 0; i <n; i++) {
            d[i][0] = i;
        }
        //当i>0,j>0
        for (int i = 1; i <n ; i++) {
            for (int j = 1; j <m ; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    //最后一个字符相同，则取上一次的结果为本次的结果。
                    d[i][j] = d[i-1][j-1];
                }
                else{
                    //取左边值
                    int a = d[i-1][j];
                    //取正上方的值
                    int b = d[i][j-1];
                    //取左上值
                    int c = d[i-1][j-1];
                    //取到左边值，正上方的值，左上值的最小值，然后加1
                    d[i][j] = Math.min(Math.min(a,b),c)+1;
                }
            }
        }
        //数组的最后一个值，即为所求的结果。
        return d[n-1][m-1];
    }

    private static List<String> getString() {
        //获取文件输入数据
        File file = new File("F:\\intellij idea\\arithmetic\\src\\input2.txt");
        BufferedReader reader = null;
        String tempStr;
        List<String> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            while((tempStr = reader.readLine())!=null){
                list.add(tempStr);
            }
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
