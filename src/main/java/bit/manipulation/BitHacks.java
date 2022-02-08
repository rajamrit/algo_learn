package bit.manipulation;

public class BitHacks {
    /*
    Represent a binary as string
     */
    public static String toBinaryString(int n) {
        return String.format("%32s", Integer.toBinaryString(n)).replaceAll(" ", "0");
    }

    /*
    Check if an integer is even or odd
    Expression n & 1 returns value 1 or 0 depending upon whether n  is odd or even.
     */
    public void testEvenOdd() {
        System.out.println(5 & 1);
        System.out.println(4 & 1);
    }

    /*
    Detect iff two integers have opposite signs or not
    Expression x ^ y(XOR) is negative if x and y have opposite signs.
    For a +ve number the leftmost bit is 0 and for a negative number it is 1.
    XOR sets the value to 1 if the bits are opposite.
     */
    public void testOppositeSigns() {
        int x = -4;
        int y = 8;
        System.out.println("x : "+ toBinaryString(x));
        System.out.println("y : "+ toBinaryString(y));
        System.out.println("x XOR y: "+ toBinaryString(x ^ y));
        boolean isOpposite = ((x ^ y) < 0);
        System.out.println("isOpposite="+ isOpposite);
    }

    /*
    Add 1 to an integer
    Expression -~x will add 1 to an integer x.
    To get negative of a number, invert its bits and add 1 to it(Negaive numbers are stored in 2's complement form)
    -x = ~x + 1;
    -~x = x + 1 (by replacing x by ~x)
     */
    public void addOne() {
        int x = 4;
        System.out.println(x + " + " + 1 + " is " + -~x);

        x = -5;
        System.out.println(x + " + " + 1 + " is " + -~x);

        x = 0;
        System.out.println(x + " + " + 1 + " is " + -~x);
    }

    /*
    Swap two numbers without using a third variable
    The idea is to use XOR operators to swap two numbers by their property x ^ x = 0
     */
    public void swapNumbers() {
        int x = 3;
        int y = 4;
        System.out.println("Before swap: x: "+ x + " y: "+ y);
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("After swap: x: "+ x + " y: "+ y);
    }


    /*
    Idea is to use bitwise <<, & and ~ operators.
    ~ ( 1 << (k-1)) we get a number with all bits set except kth bit.Then do a bitwise AND of this expression
    with n. i.e n  & ~(1 << (k-1))
     */
    public void turnOffKthBitOfANumber() {
        int n = 20;
        int k = 3;
        System.out.println("n in binary="+ toBinaryString(n));
        n = n & ~(1 << (k-1));
        System.out.println("after: n in binary="+ toBinaryString(n));
    }

    /*
    n | (1 << (k-1)) The second part sets only the kth bit.
     */
    public void turnOnKthBitOfANumber() {
        int n = 20;
        int k = 4;
        System.out.println(n + " in binary is " + Integer.toBinaryString(n));
        n = n | (1 << (k-1));
        System.out.println("after: "+ n + " in binary is " + Integer.toBinaryString(n));
    }

    /*
    Check if kth bit is set for a number
     */
    public void checkKthBitIsSet() {
        int n = 20;
        int k = 3;
        System.out.println(n + " in binary is " + Integer.toBinaryString(n));
        boolean isSet = (n & (1 << (k - 1))) != 0;
        System.out.println("isSet: "+ isSet);
    }

    /*
    The idea is to use bitwise ^ and << operators.
    By using the expression 1 << (k - 1), we get a number with all bits 0,
    except the k'th bit.
    If we do bitwise XOR of this expression with n, i.e., n ^ (1 << k), we can easily
    toggle its k'th bit as 0 ^ 1 = 1 and 1 ^ 1 = 0.
     */
    public void toggleKthBit() {
        int n = 20;
        int k = 3;
        System.out.println(n + " in binary is " + Integer.toBinaryString(n));
        n = n ^ (1 << (k-1));
        System.out.println(n + " in binary is " + Integer.toBinaryString(n));
    }


    public void run() {
        toggleKthBit();
    }

    public static void main(String[] args) throws Exception {
        BitHacks bh = new BitHacks();
        bh.run();
    }
}

