package gog.segmenttree;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.log4j.BasicConfigurator;

import java.util.Arrays;

/**
 *
 */
public class BasicExample {

    private int constructSegmentTreeUtil(int[] arr, int left, int right, int[] st, int stPos) {
        //System.out.println("left="+ left + ",right="+ right + ",stPos="+ stPos);
        //System.out.println(Arrays.toString(st));
        int mid = left + (right - left) / 2;
        if(left==right) {
            st[stPos] = arr[left];
            return arr[left];
        }

        st[stPos] = constructSegmentTreeUtil(arr, left, mid, st, 2*stPos+1) +
        constructSegmentTreeUtil(arr, mid+1, right, st, 2*stPos+2);
        return st[stPos];

    }

    /*
    ss, se -> start and end indices of the original array represented by current node
    stPos -> Index of the current node in the segment tree
    qs, qe -> Start and end indices in the original array which we want to sum up
     */
    private int getSumUtil(int[] st, int ss, int se, int stPos, int qs, int qe) {
        // If the represented segment is within the qs and qe
        if(qs <= ss && se <= qe) {
            return st[stPos];
        }

        // If the represented segment is completely outside of the query
        if(se < qs || ss > qe) {
            return 0;
        }

        int mid = ss + (se - ss)/2;
        return getSumUtil(st, ss, mid, 2*stPos+1, qs, qe) +
                getSumUtil(st, mid+1, se, 2*stPos+2, qs, qe);

    }

    protected int getSum(int[] st, int n, int qs, int qe) {
        if(qs < 0 || qe > n-1 || qe < qs) {
            System.out.println("Invalid Input");
            return 0;
        }
        return getSumUtil(st, 0, n-1, 0, qs, qe);
    }

    /*
    ss, se -> start and end of the original array represented by segment tree node at stPos
     */
    private void updateValueUtil(int[] st, int ss, int se, int stPos, int index, int diff) {
        if(index < ss || index > se) {
            return;
        }
        st[stPos] += diff;
        if(ss != se) {
            int mid = ss + (se - ss)/2;
            updateValueUtil(st, ss, mid, 2*stPos+1, index, diff);
            updateValueUtil(st, mid+1, se,  2*stPos+2, index, diff);

        }
    }

    protected void updateValue(int[] arr, int n, int i, int newVal) {
        if(i<0 || i > n-1) {
            return;
        }

        // Get the difference between new and old value
        int diff = newVal - arr[i];
        // update the array
        arr[i] = newVal;
        // Update the values of nodes in segment tree


    }

    protected int[] constructSegmentTree(int[] arr, int len) {
        // Array to hold the segment tree
        int[] st;
        // Allocate space for st; Number of elements in segment tree is 2^ceil((logn))-1
        int heightTree = (int)Math.ceil(Math.log(len)/Math.log(2));
        System.out.println("Height Required: "+ heightTree);
        int maxSizeST = 2 * (int)Math.pow(2, heightTree) -1 ;
        System.out.println("Size of segment tree is "+ maxSizeST);
        st = new int[maxSizeST];
        Arrays.fill(st, -1);

        constructSegmentTreeUtil(arr, 0, len-1, st, 0);
        return st;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{1,3,5,7,9,11};
        int n = arr.length;
        BasicExample example = new BasicExample();
        int[] st = example.constructSegmentTree(arr, n);
        System.out.println(Arrays.toString(st));

        System.out.println("Sum of values in given range = " +
                example.getSum(st, n, 1, 3));
    }
}
