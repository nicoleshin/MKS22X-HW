public class Quick {

    public static int quickselect(int[] data, int k) {
        k = k-1;
        int start, end;
        start = 0;
        end = data.length-1;
        int pivotIndex = part(data, start, end);
        while (pivotIndex != k) {
            print(data);
            if (pivotIndex > k) {
                end = pivotIndex-1;
            } else {
                start = pivotIndex+1;
            }
            pivotIndex = part(data, start, end);
        }
        return data[pivotIndex];
    }

    public static int part(int[] data, int start, int end) {
        int pivotPoint = (int) ((end - start + 1) * Math.random() + start);
        int pivot = data[pivotPoint];
        System.out.println("Pivot: " + pivot);
        swap(data, pivotPoint, end);
        int wall = start;
        for (int i = start; i <= end; i++) {
            if (data[i] < pivot) {
                swap(data, wall, i);
                wall++;
            }
        }
        swap(data, wall, end);
        return wall;
    }

    public static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void main(String[] args) {
        int[] ary = new int[]{5, 7, 1, 3, 4, 2, 6, 0};
        print(ary);
        System.out.println(quickselect(ary,4));
    }

    public static void print(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + ", ");
        }
    }
}
