package com.example.java.ag.search;

import java.lang.reflect.Array;

/**
 * @author Liq
 * @date 2020/7/16
 */
public class SearchDemo {


    public static void main(String[] args) {

        int[] nums = {1,2,3,4,5,6,7,8,9,10,11};
        System.out.println(biSearch(nums, 6));
    }


    private static int biSearch(int[] nums,int n) {

       int low = 0;
       int hight = nums.length - 1;

       int middle = 0;
       while(low <= hight) {
           middle = (low + hight) >> 1;
           if(nums[middle] == n) {
               return middle;
           } else if (nums[middle] < n) {
               low = middle + 1;
           } else {
               hight = middle -1;
           }
       }
       return -1;
    }
}
