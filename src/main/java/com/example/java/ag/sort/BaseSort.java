package com.example.java.ag.sort;

import java.util.Arrays;

/**
 * @author Liq
 * @date 2020/7/16
 */
public class BaseSort {

    public static void main(String[] args) {

        int[] nums = {1, 2, 45, 2, 34, 5, 21, 654, 23, 6, 132, 65, 2, 433, 2436, 246, 243, 62};
        int[] ints = selectSort(nums);
        System.out.println(Arrays.toString(ints));

    }


    // 冒泡排序
    private static int[] bubbleSort(int[] nums) {
        int n = nums.length;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            // 比较相邻
            for (int j = 1; j < n - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    int tmp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = tmp;

                    // 有限次比较之后没有发生交换，说明数组已经有序，可以退出循环了
                    flag = false;
                }
            }
            if(flag) {
                break;
            }

        }
        return nums;
    }

    // 插入排序
    private static int[] insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int index = i -1;
            while(index >= 0 && tmp < nums[index]) {
                nums[index + 1] = nums[index];
                index --;
            }
            nums[index + 1] = tmp;
        }
        return nums;
    }

    // 快速排序
    private static int[] quickSort(int[] nums,int low,int hight) {
        int start = low;
        int end = hight;

        int key = nums[start];

        while(start < end) {

            // 从后向前
            while(start < end && nums[end] >= key) {
                // 找到一个比关键值小的数
                end --;
            }
            if(nums[end] <= key) {
                int tmp = nums[end];
                nums[end] = nums[start];
                nums[start] = tmp;
            }

            // 从前往后
            while (start < end && nums[start] <= key) {
                // 找到一个大的
                start++;
            }
            if(nums[start] >= key) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
            }

        }

        if(start > low){
            quickSort(nums,low,start -1);
        }
        if(end < hight){
            quickSort(nums,end + 1,hight);
        }

        return nums;
    }

    // 选择排序
    private static int[] selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i+1; j < nums.length ; j++) {
                if(nums[j] < nums[min]) {
                    min = j;
                }
            }
            if(min != i) {
                int tmp = nums[i];
                nums[i] = nums[min];
                nums[min] = tmp;
            }
        }
        return nums;
    }
}
