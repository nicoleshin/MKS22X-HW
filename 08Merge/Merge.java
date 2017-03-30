public class Merge {

    public static void print(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + ", ");
        }
        System.out.println();
    }

    public static void mergesort(int[] data) {
        if (data.length != 1) {
            //print(data);
            int[] left = subarray(data, 0, data.length/2);
            int[] right = subarray(data, data.length/2, data.length);
            mergesort(left);
            mergesort(right);
            merge(left, right, data);
        }
    }

    private static int[] subarray(int[] ary, int start, int end) {
        int[] ret = new int[end-start];
        for (int i = start; i < end; i++) {
            ret[i-start] = ary[i];
        }
        return ret;
    }

    private static void merge(int[] left, int[] right, int[] dest) {
        int li = 0;
        int ri = 0;
        for (int i = 0; i < dest.length; i++) {
            if (ri >= right.length) {
                dest[i] = left[li];
                li++;
            } else if (li >= left.length) {
                dest[i] = right[ri];
                ri++;
            } else if (right[ri] < left[li]) {
                dest[i] = right[ri];
                ri++;
            } else {
                dest[i] = left[li];
                li++;
            }
        }
    }
}
