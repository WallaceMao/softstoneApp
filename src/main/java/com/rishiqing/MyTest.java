package com.rishiqing;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/29
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class MyTest {

    public static void main(String[] args){
        String str = "abcabx";
        int[] n = getNext(str);
        printArray(n);
    }

    public static int[] getNext(String t){

        char[] p = t.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                next[++j] = ++k;

            } else {

                k = next[k];

            }

        }

        return next;
    }

    public static void printArray(int[] arr){
        String str = "[";
        for(int i = 0; i < arr.length; i++){
            str += arr[i];
            if(i != arr.length - 1){
                str += ",";
            }
        }
        str += "]";
        System.out.println(str);
    }
}
