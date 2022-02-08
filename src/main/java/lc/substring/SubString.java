package leetcode.stringops;

import java.util.Arrays;

public class SubString {
    private boolean isPalindrome(String str) {
        if(str.isEmpty()) return true;
        str = str.toLowerCase();
        for(int left=0,right=str.length()-1; left<right; left++,right--) {
            if(str.charAt(left) != str.charAt(right))
                return false;
        }
        return true;
    }

    /*
    Longest palindromic substring
    The below is a brute force approach
    Pick all possible starting end ending positions and verify if its a palindrome
     */

    /*
    Dynamic Programming
    We can improve upon the above solution of brute force. We can avoid unneccsary re-computation while
    validating palindromes.  If we already knew that "bab" is a palindrome, it is obvious that "ababa"
    must be a palindrome since the two left and right end letters are the same.
    Two base cases
    P(i,i) = true
    P(i,i+1) = (S[i] == S[i+1])
    Time complexity: O(n^2)
    Space complexity: O(n^2)
     */
    private int[][] dp;
    public void dynamicProgrammingPalindrome(String str) {
        for(int i=0; i<str.length(); i++) {
            dp[i] = new int[str.length()];
            Arrays.fill(dp[i], -1);
        }
        for(int i=0; i<str.length()-1; i++) {
            for(int j=0; j<str.length()-1; j++) {
                computeGridValue(str, i, j);
            }
        }
        int maxLen = 0;
        int pos_i = -1; int pos_j = -1;

    }
    private int computeGridValue(String str, int i, int j) {
        if(dp[i][j] > -1) return dp[i][j];

        if(i==j) {
            dp[i][j] = 1;
        }
        if(j == i+1) {
            if(str.charAt(j) == str.charAt(i)) {
                dp[i][j] = 2;
            }
        }
        return dp[i][j];
    }


}
