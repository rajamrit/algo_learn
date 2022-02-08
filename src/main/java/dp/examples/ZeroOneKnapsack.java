package dp.examples;

import java.util.Arrays;

/**
 * Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack
 * that has a capacity ‘C’. The goal is to get the maximum profit from the items in the knapsack.
 * Each item can only be selected once, as we don’t have multiple quantities of any item.
 * Problem Statement:
 * Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset
 * of these items which will give us maximum profit such that their cumulative weight is not more
 * than a given number ‘C’. Write a function that returns the maximum profit. Each item can only be
 * selected once, which means either we put an item in the knapsack or skip it.
 */
public class ZeroOneKnapsack {
    /*
    Basic Solution; Brute force
    Try all combinations of the given items.
        for each item 'i'
        create a new set which INCLUDES item 'i' if the total weight does not exceed the capacity, and
        recursively process the remaining capacity and items
        create a new set WITHOUT item 'i', and recursively process the remaining items
        return the set from the above two sets with higher profit
     */
    public int solveKnapsackRecursiveBruteForce(int[] profits, int[] weights, int capacity) {
        return knapsackRecursive(profits, weights, capacity, 0);
    }
    /*
    Time complexity of the above approach => O(2^n)
    Space: O(n) used by the recursion stack
     */

    public int knapsackRecursive(int[] profits, int[] weights, int remainingCapacity, int currentIndex) {
        if(remainingCapacity <= 0 || currentIndex >= profits.length) {
            return 0;
        }
        // Recursive call after choosing the element at the current index
        // If the weight of the current element is less than remaining capacity then only we include it
        int profit1 = 0;
        if(weights[currentIndex] <= remainingCapacity) {
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights,
                    remainingCapacity - weights[currentIndex], currentIndex+1);
        }
        // Recursively call after excluding the element at the current index
        int profit2 = knapsackRecursive(profits, weights, remainingCapacity, currentIndex+1);
        return Math.max(profit1, profit2);
    }


    /*
    Top down dynamic programming with memoization
    In the recursive call stack of the above method, there are repetitions. Profits and Weights arrays remains the same
    but there are multiple calls made for same remainingCapacity and currentIndex.
    Memoization is when we store the results of all the previously solved sub-problems ad return the results from memory
    if we encounter a problem that has already been solved.
    We need to store results for every sub array(i.e for every possible index i and for every possible capacity C
     */
    public int solveKnapsackDPTopDownMemoization(int[] profits, int[] weights, int capacity) {
        int[][] dp = new int[profits.length][capacity+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
        return knapsackRecursiveDPMemoization(dp, profits, weights, capacity, 0);
    }

    public int knapsackRecursiveDPMemoization(int[][] dp, int[] profits, int[] weights, int remainingCapacity, int currentIndex) {
        if(remainingCapacity <= 0 || currentIndex >= profits.length) {
            return 0;
        }

        // If we have already solved the same problem, return the result from memory
        if(dp[currentIndex][remainingCapacity] != -1) {
            return dp[currentIndex][remainingCapacity];
        }
        // Recursive call after choosing the element at the current index
        // If the weight of the current element is less than remaining capacity then only we include it
        int profit1 = 0;
        if(weights[currentIndex] <= remainingCapacity) {
            profit1 = profits[currentIndex] + knapsackRecursiveDPMemoization(dp, profits, weights,
                    remainingCapacity - weights[currentIndex], currentIndex+1);
        }
        // Recursively call after excluding the element at the current index
        int profit2 = knapsackRecursiveDPMemoization(dp, profits, weights, remainingCapacity, currentIndex+1);
        return Math.max(profit1, profit2);
    }
    /*
    What is the time and space complexity of the above solution?
    Since the memoization array stores the results of the sub problems, we can conclude that we will not have more than
    N * C sub-problems. Time complexity is O(N*C)
    Space is also O(N*C) + space required for recursion call stack.
     */



    public static void main(String[] args) {
        ZeroOneKnapsack ks = new ZeroOneKnapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxWeightCapacity = 7;
        int maxProfit = ks.solveKnapsackDPTopDownMemoization(profits, weights, maxWeightCapacity);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsackDPTopDownMemoization(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
