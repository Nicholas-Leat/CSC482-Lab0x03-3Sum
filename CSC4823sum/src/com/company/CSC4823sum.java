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
        threeSumTest();
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
        //System.out.println(count);
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
        //System.out.println(count);
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
        //System.out.println(count);

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
    public static void threeSumTest(){
        int[] N = new int[1000];
        int Nval = 4;
        double maxN = 10000000;
        int iter = 0;
        //brute force algorithm
        double[] bruteTime = new double[1000];
        double[] bruteDR = new double[1000];
        double[] bruteEDR = new double[1000];
        //Faster 3sum algorithm
        double[] fasterTime = new double[1000];
        double[] fasterDR = new double[1000];
        double[] fasterEDR = new double[1000];
        //Fastest 3sum algorithm
        double[] fastestTime = new double[1000];
        double[] fastestDR = new double[1000];
        double[] fastestEDR = new double[1000];
        //time control
        double timeBefore;
        double timeAfter;
        double totalTime = 0;
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-100s %-100s %-100s\n","Brute 3sum", "Faster 3sum", "Fastest 3sum");
        System.out.format("%-10s %-30s %-30s %-27s %-30s %-30s %-38s %-30s %-30s %-30s\n", "N", "Time", "Doubling Ratios", "Expected Doubling Ratios", "Time", "Doubling Ratios", "Expected Doubling Ratios", "Time", "Doubling Ratios", "Expected Doubling Ratios");

        while(Nval < maxN){
            //System.out.printf("Generating list of length %d\n",Nval);
            int[] list = Generatelist(-100,100,Nval);
            N[iter] = Nval;

            //System.out.println("Fastest 3sum");
            timeBefore = getCpuTime();
            Fastest(0,list);
            timeAfter = getCpuTime();
            totalTime = timeAfter - timeBefore;
            fastestTime[iter] = totalTime;

            //System.out.println("Faster 3sum");
            timeBefore = getCpuTime();
            Faster(0,list);
            timeAfter = getCpuTime();
            totalTime = timeAfter - timeBefore;
            fasterTime[iter] = totalTime;


            //Brute 3sum
            //System.out.println("Brute 3sum");
            timeBefore = getCpuTime();
            Bruteforce(0,list);
            timeAfter = getCpuTime();
            totalTime = timeAfter - timeBefore;
            bruteTime[iter] =  totalTime;

            if(iter != 0){
                bruteDR[iter] = (bruteTime[iter]/bruteTime[iter-1]);
                fasterDR[iter] = (fasterTime[iter]/fasterTime[iter-1]);
                fastestDR[iter] = (fastestTime[iter]/fastestTime[iter-1]);
                //expected Doubling Ratios
                bruteEDR[iter] = ((double)Nval * (double)Nval * (double)Nval)/((double)N[iter-1] * (double)N[iter-1] *(double) N[iter-1]);
                fasterEDR[iter] = (((double)Nval * (double)Nval) * Math.log(Nval))/(((double)N[iter-1] * (double)N[iter-1]) *Math.log(N[iter-1]));
                fastestEDR[iter] = ((double)Nval * (double)Nval)/((double)N[iter-1] * (double)N[iter-1]);

            }else{
                bruteDR[0] = 0;
                fasterDR[0] = 0;
                fastestDR[0] = 0;
                bruteEDR[0] = 0;
                fasterEDR[0] = 0;
                fastestEDR[0] =0;
            }

            //print Values
            System.out.format("%-10d %-30f %-30f %-27f %-30f %-30f %-38f %-30f %-30f %-30f\n",Nval, bruteTime[iter], bruteDR[iter], bruteEDR[iter], fasterTime[iter], fasterDR[iter], fasterEDR[iter], fastestTime[iter], fastestDR[iter], fastestEDR[iter]);

            //Update values
            iter++;
            Nval = Nval * 2;
            bruteEDR[0] = 1;
            fasterEDR[0] = 1;
            fastestEDR[0] =1;
        }/**/

    }
    public static long getCpuTime( ) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

        return ((ThreadMXBean) bean).isCurrentThreadCpuTimeSupported( ) ?

                bean.getCurrentThreadCpuTime( ) : 0L;

    }
}
