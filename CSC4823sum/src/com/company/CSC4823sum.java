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
        Bruteforce(0,myList);

    }
    public static void Bruteforce(int sum, int[] list){
        //get rid of duplicates if time
        for(int i = 0; i < list.length-2; i++){
            for(int j = i+1; j < list.length-1; j++){
                for(int k = j+1; k < list.length; k++){
                    if(list[i] + list[j] + list[k] == 0){
                        System.out.println("Triplet is " + list[i] + ", " + list[j] + ", "+ list[k]);
                    }
                }
            }
        }
    }
    public static void Faster(int sum){

    }
    public static void Fastest(int sum){

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
}
