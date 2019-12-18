package cn.wyu.experiment;

import java.io.*;
import java.util.Arrays;

public class Test01 {
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        test();

    }

    /**
     * 实验一：众数问题
     * 1.
     */
    public static void test() {
        //获取文件输入内容
        File file = new File("F:\\intellij idea\\arithmetic\\src\\input.txt");
        BufferedReader reader = null;
        String tempStr;
        int []array = new int[(int) file.length()];
        int i=0;
        try {
            reader = new BufferedReader(new FileReader(file));
            while((tempStr = reader.readLine())!=null) {
                array[i] = Integer.parseInt(tempStr);
                i++;
            }
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(array));
    }

}
