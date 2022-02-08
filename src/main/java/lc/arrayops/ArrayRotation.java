package leetcode.arrayops;

import java.util.Arrays;

public class ArrayRotation {
    /*
    Given an array, rotate the array to the right by k steps, where k is non-negative.
    Input: nums = [1,2,3,4,5,6,7], k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4]
     */
    /*
    In this approach; a trick is used where in we first reverse the whole array and then reverse two parts of the array
    In this way, constant additional memory is required. Array is iterated twice with time complexity of O(n)
     */
    public void firstWay(int[] nums, int k) {
        if(nums.length <= 1) {
            return;
        }
        int step = k % nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, step-1);
        reverse(nums, step, nums.length -1);
    }

    private void reverse(int[] nums, int n, int m) {
        while(n < m) {
            int t = nums[n];
            nums[n] = nums[m];
            nums[m] = t;
            n++;
            m--;
        }
    }

    private void reverse_bitwiseXOR(int[] nums, int n, int m) {
        while(n < m){
            nums[n] ^= nums[m];
            nums[m] ^= nums[n];
            nums[n] ^= nums[m];
            n++;
            m--;
        }
    }

    /*
    153. Find Minimum in Rotated Sorted Array
    Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
    For example, the array nums = [0,1,2,4,5,6,7] might become:

    [4,5,6,7,0,1,2] if it was rotated 4 times.
    [0,1,2,4,5,6,7] if it was rotated 7 times.
    Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results
    in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

    Given the sorted rotated array nums of unique elements, return the minimum element of this array.

    You must write an algorithm that runs in O(log n) time.
     */
    /*
    Time complexity: Same as binary search: O(log N)
    Space complexity: O(1)
     */


    public static void main(String[] args) throws Exception {
        int[] arr = new int[] {1,2,3,4,5,6,7};
        System.out.println("Array original: "+ Arrays.toString(arr));
        ArrayRotation ar = new ArrayRotation();
        ar.reverse_bitwiseXOR(arr, 0, arr.length-1);
        System.out.println("Array Reversed: "+ Arrays.toString(arr));
        ar.firstWay(arr, 3);
        System.out.println("Array Reversed: "+ Arrays.toString(arr));
    }


}
