package com.company;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Random;

public class CSC4823sum {


    public static void main(String[] args) throws FileNotFoundException{
        int sum = 0;
        int N = 20;
        int[] myList = Generatelist(-10,10,N);
        double before = getCpuTime();
        Bruteforce(sum, myList);
        double after = getCpuTime();
        double total = after - before;
        System.out.printf("Total time : %f\n",total);
        before = getCpuTime();
        Faster(sum,myList);
        after = getCpuTime();
        total = after - before;
        System.out.printf("Total time : %f\n",total);
        before = getCpuTime();
        Fastest(sum, myList);
        after = getCpuTime();
        total = after - before;
        System.out.printf("Total time : %f\n",total);
    }
    public static void Bruteforce(int sum, int[] list){
        int length = list.length;
        int[][][] found = new int[length][length][length];
        for(int i = 0; i < list.length-2; i++){
            for(int j = i+1; j < list.length-1; j++){
                for(int k = j+1; k < list.length; k++){
                    if(list[i] + list[j] + list[k] == 0 && found[i][j][k] != 1){
                        System.out.println("Triplet is " + list[i] + ", " + list[j] + ", "+ list[k]);
                        found[i][j][k] = 1;
                        found[i][k][j] = 1;
                        found[j][k][i] = 1;
                        found[j][i][k] = 1;
                        found[k][j][i] = 1;
                        found[k][i][j] = 1;
                    }
                }
            }
        }
    }
    public static void Faster(int sum, int[] list){
        int length = list.length;
        int[][][] found = new int[length][length][length];

        for(int i = 0; i < list.length-2; i++){
            for(int j = i+1; j < list.length/2; j++){
                for (int k = list.length/2; k < list.length; k++) {
                    if (list[i] + list[j] + list[k] == 0 && found[i][j][k] != 1) {
                        System.out.println("Triplet is " + list[i] + ", " + list[j] + ", " + list[k]);
                        found[i][j][k] = 1;
                        found[i][k][j] = 1;
                        found[j][k][i] = 1;
                        found[j][i][k] = 1;
                        found[k][j][i] = 1;
                        found[k][i][j] = 1;
                    }
                }
            }
        }
    }
    public static void Fastest(int sum,int[] list){
        //sort list
        list = sort(list);
        int[] newList = removeDuplicates(list,list.length);
        int k;
        //two loops
        for(int i = 0; i < newList.length-1; i++){
            for(int j = 0; j < newList.length; j++){
                int val = (newList[i] + newList[j])*(-1);
                k = binarySearch(newList,val);
                if(k != -1 && newList[i] + newList[j] + newList[k] == 0){
                    System.out.println("Triplet is " + newList[i] + ", " + newList[j] + ", " + newList[k]);
                }
            }
        }
    }
    public static int[] removeDuplicates(int[] list, int length){
        int[] temp = new int[length];
        int j = 0;
        for(int i = 0; i < length-1; i++){
            if(list[i] != list[i+1]){
                temp[j++] = list[i];
            }
        }
        temp[j++] = list[length-1];
        int[] returnArr = new int[j];
        for(int i = 0; i< j; i++){
            returnArr[i] = temp[i];
        }

        return returnArr;
    }
    public static int binarySearch(int[] list, int val){
        int length = list.length;
        int l = 0, r = length-1;

        while(l<=r){
            int m = l + (r-1)/2;

            if (list[m] == val){
                return m;
            }
            if(list[m] < val){
                l = m + 1;
            }else{
                r = m -1;
            }
        }
        return -1;
    }
    public static int[] Generatelist(int min, int max, int length){
        int[] list = new int[length];
        Random r = new Random();
        int result;
        for(int x = 0; x < length; x++){
            //gives us a random int between min (inclusive) and max(exclusive)
            result = r.nextInt(2*max)+min;
            list[x] = result;

        }
        return list;
    }
    public static int[] sort(int[] list){
        int length = list.length;

        for(int i = 0; i < length; i++){
            int min_idx = i;
            for(int j = i+1; j < length; j++){
                if(list[j] < list[min_idx]){
                    min_idx = j;
                }
            }
            int temp = list[min_idx];
            list[min_idx] = list[i];
            list[i] = temp;
        }
        return list;
    }
    public static long getCpuTime( ) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

        return ((ThreadMXBean) bean).isCurrentThreadCpuTimeSupported( ) ?

                bean.getCurrentThreadCpuTime( ) : 0L;

    }
}
