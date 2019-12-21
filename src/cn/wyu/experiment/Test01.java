package cn.wyu.experiment;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 实验一：众数问题
 * 1.读取数据，快速排序
 * 2.采用递归和分治策略，求取众数和重数
 * 3.把所求的结果，保存到output.txt文件中
 */
public class Test01 {
    static int num;     //存储众数
    static int sum;     //存储重数
    public static void main(String[] args) {
        // TODO 自动生成的方法存根


        //1.读取数据，并进行排序。
        int[] array = getDatas();

        //2.采用递归和分治策略，求取众数和重数
        mode(array,0,array.length-1);
        System.out.println("众数为："+num);
        System.out.println("重数为："+sum);

        //3.把所求的结果，保存到output.txt文件中
        saveDatas(num,sum);


    }

    private static void saveDatas(int num, int sum) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("F:\\intellij idea\\arithmetic\\src\\output.txt"));
            dataOutputStream.write(num);
            dataOutputStream.write(sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] getDatas() {
        //获取文件输入数据
        File file = new File("F:\\intellij idea\\arithmetic\\src\\input.txt");
        BufferedReader reader = null;
        String tempStr;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            while((tempStr = reader.readLine())!=null) {
                   list.add(Integer.parseInt(tempStr));
            }
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);

        }

        //对取到的数据进行排序
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        return array;
    }
    public static void mode(int[] a,int p,int q){
        int middleNum = (p+q)/2;             //取中间的索引
        int mulNum = count(a,middleNum);     //当前中间数的重数个数
        int firstNum = start(a,middleNum);   //当前中间数的出现的首次位置
        if(mulNum>sum){
            sum = mulNum;
            num = a[middleNum];
        }
        //右边递归
        if(q-(firstNum+mulNum)>mulNum){
            mode(a,q-(firstNum+mulNum),q);
        }
        //左边递归
        if(firstNum>mulNum){
            mode(a,0,firstNum);
        }

    }

    private static int start(int[] a, int middleNum) {
        int x = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i]==middleNum){
                x = i;
                break;
            }

        }
        return x;
    }

    private static int count(int[] a, int middleNum) {
        int countNum=0;
        for (int i = 0; i < a.length; i++) {
            if(a[i]==middleNum){
                countNum++;
            }

        }
        return countNum;
    }

}
