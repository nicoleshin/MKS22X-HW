import java.lang.IllegalArgumentException;

public class Recursion {
    public static double sqrt(double n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return 0;
        } else {
            return squareRoot(n, 1);
        }
    }

    public static String name() {
        return "Shin,Nicole";
    }

    private static double squareRoot(double target, double guess) {
        if (isCloseEnough(guess*guess, target)) {
            return guess;
        } else {
            return squareRoot(target, (target/guess + guess)/2);
        }
    }

    private static boolean isCloseEnough(double a, double b) {
        return Math.abs(a-b) < (a*0.000000001);
    }

    public static void main(String[] args) {
        System.out.println(sqrt(5));
        System.out.println(Math.sqrt(5));
        System.out.println(sqrt(3));
        System.out.println(Math.sqrt(3));
        System.out.println(sqrt(1234));
        System.out.println(Math.sqrt(1234));
        System.out.println(sqrt(2345));
        System.out.println(Math.sqrt(2345));
    }
}
