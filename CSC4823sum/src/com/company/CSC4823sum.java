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
    public static void Bruteforce(int sum, int[] mylist){
        mylist = sort(mylist);
        int[] list = removeDuplicates(mylist,mylist.length);
        int length = list.length;
        int[][][] found = new int[length][length][length];
        int count = 0;

        for(int i = 0; i < list.length; i++){
            for(int j = 0; j < list.length; j++){
                for(int k = 0; k < list.length; k++){
                    if(list[i] + list[j] + list[k] == 0 && found[i][j][k] != 1 && (i != j && j != k && k != i)){
                        //System.out.println("Triplet is " + list[i] + ", " + list[j] + ", "+ list[k]);
                        found[i][j][k] = 1;
                        found[i][k][j] = 1;
                        found[j][k][i] = 1;
                        found[j][i][k] = 1;
                        found[k][j][i] = 1;
                        found[k][i][j] = 1;
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
    public static void Faster(int sum, int[] mylist){
        mylist = sort(mylist);
        int[] list = removeDuplicates(mylist,mylist.length);
        int length = list.length;
        int[][][] found = new int[length][length][length];
        int count = 0;
        int zero = 0;
        for(int i = 0; i < length; i++){
            if(list[i] == 0 || (list[i] > 0 && list[i-1] < 0)){
                zero = i;
            }
        }
        for(int i = 0; i < zero; i++){
            for(int j = 0; j < length; j++){
                for(int k = 0; k < length; k++){
                    if(list[i] + list[j] + list[k] == 0 && found[i][j][k] != 1 && (i != j && j != k && k != i)){
                        //System.out.println("Triplet is " + list[i] + ", " + list[j] + ", "+ list[k]);
                        found[i][j][k] = 1;
                        found[i][k][j] = 1;
                        found[j][k][i] = 1;
                        found[j][i][k] = 1;
                        found[k][j][i] = 1;
                        found[k][i][j] = 1;
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
    public static void Fastest(int sum,int[] mylist){
        mylist = sort(mylist);
        int[] list = removeDuplicates(mylist,mylist.length);
        int length = list.length;
        int[][][] found = new int[length][length][length];
        int count = 0;
        int zero = 0;
        for(int i = 0; i < length; i++){
            if(list[i] == 0 || (list[i] > 0 && list[i-1] < 0)){
                zero = i;
            }
        }
        for(int i = 0; i < zero; i++){
            for(int j = zero; j < length; j++){
                for(int k = 0; k < length; k++){
                    if(list[i] + list[j] + list[k] == 0 && found[i][j][k] != 1 && (i != j && j != k && k != i)){
                        //System.out.println("Triplet is " + list[i] + ", " + list[j] + ", "+ list[k]);
                        found[i][j][k] = 1;
                        found[i][k][j] = 1;
                        found[j][k][i] = 1;
                        found[j][i][k] = 1;
                        found[k][j][i] = 1;
                        found[k][i][j] = 1;
                        count++;
                    }
                }
            }
        }
        System.out.println(count);

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
        if (list[length-1] < val){
            return -1;
        }else if(list[0] > val){
            return -1;
        }
        while(l<=r){
            int m = (l+r)/2;

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
