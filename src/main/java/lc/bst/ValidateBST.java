package leetcode.bst;

import leetcode.common.TreeNode;

/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 */

/*
There is a condition where an inner children of left tree can be a max value for that subtree. All elements of
the right subtree has to be greater than the value
 */
public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minValue, long maxValue) {
        if(root == null) return true;
        if(root.val >= maxValue || root.val <= minValue) return false;
        return isValidBST(root.left, minValue, root.val) && isValidBST(root.right, root.val, maxValue);
    }
}
