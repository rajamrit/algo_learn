package leetcode.bst;


import leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BSTFunctions {
    private static final Logger logger = LoggerFactory.getLogger(BSTFunctions.class);

    /*
    Iterative Inorder traversal using stack
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    /*
    Find Kth smallest element in BST; using the above iterative inorder traversal
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) break;
            root = root.right;
        }
        if (root != null) return root.val;
        return -1;
    }

    /*
    The above iterative approach can also be used to validate if a given binary tree is a BST
    Trick is to do an inorder traversal of the tree and check that the value of each node visited is greater than the
    value of the previous node.
     */
    public boolean isValidBSTIterative(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && pre.val > root.val) return false;
            pre = root;
            root = root.right;

        }
        return true;
    }

    /*
    Recursive version of the above approach
     */
    /*public boolean isValidBSTInternalRecursive(TreeNode root, TreeNode pre) {
        if(root == null) return true;
        if(pre != null && root.val >= pre.val) return false;

    }
    public boolean isValidBSTRecursive(TreeNode root) {
        if(root == null) return true;
        TreeNode prev = null;
        if(isValidBSTInternalRecursive(root.left, prev))

    }*/

    private String printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < arr.length; idx++) {
            sb.append(arr[idx]).append(",");
        }
        if(sb.length()>0) {
            return sb.substring(0, sb.length()-1);
        }
        return "";
    }

    public void printBinaryTree(TreeNode root) {

    }

    public TreeNode constructSampleBST() {
        int[] arr = new int[] {99,35,19,0,11,40,5};
        logger.info("original arr: "+ printArray(arr));
        Arrays.sort(arr);
        logger.info("sorted arr: "+ printArray(arr));
        TreeNode root = constructBST(arr, 0, arr.length-1, null);
        return root;
    }

    private TreeNode constructBST(int[] arr, int start, int end, TreeNode root) {
        if(start > end) {
            return null;
        }
        int mid = (start + end)/2;
        if(root == null) {
            root = new TreeNode(arr[mid]);
        }
        root.left = constructBST(arr, start, mid-1, root.left);
        root.right = constructBST(arr, mid+1, end, root.right);
        return root;
    }



    public static void main(String[] args) throws Exception {
        BSTFunctions runner = new BSTFunctions();
        TreeNode root = runner.constructSampleBST();
    }
}
