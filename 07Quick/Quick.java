public class Quick {

    public static int quickselect(int[] data, int k) {
        return selectPart(data, 0, data.length-1, k);
    }

    public static int selectPart(int[] data, int start, int end, int k) {
        int pivotPoint = (int) ((end - start + 1) * Math.random() + start);
        int pivot = data[pivotPoint];
        swap(data, pivotPoint, start);
        int i = start;
        int greaterWall = end;
        int lesserWall = start;
        while (i <= greaterWall) {
            if (data[i] == pivot) {
                i++;
            } else if (data[i] < pivot) {
                swap(data, i, lesserWall);
                lesserWall++;
                i++;
            } else {
                swap(data, i, greaterWall);
                greaterWall--;
            }
        }
        if (k > greaterWall) {
            return selectPart(data, greaterWall, end, k);
        } else if (k < lesserWall) {
            return selectPart(data, start, lesserWall, k);
        } else {
            return pivot;
        }
    }

    public static void quicksort(int[] data) {
        sortPart(data, 0, data.length-1);
    }

    public static void sortPart(int[] data, int start, int end) {
        if (end-start > 1) {
            int pivotPoint = (int) ((end - start + 1) * Math.random() + start);
            int pivot = data[pivotPoint];
            swap(data, pivotPoint, start);
            int i = start;
            int greaterWall = end;
            int lesserWall = start;
            while (i <= greaterWall) {
                if (data[i] == pivot) {
                    i++;
                } else if (data[i] < pivot) {
                    swap(data, i, lesserWall);
                    lesserWall++;
                    i++;
                } else {
                    swap(data, i, greaterWall);
                    greaterWall--;
                }
            }
            sortPart(data, start, lesserWall);
            sortPart(data, greaterWall, end);
        }
    }

    public static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

/*
    public static void main(String[] args) {
        int[] ary = new int[500];
        for (int i=0; i<500; i++) {
            ary[i] = i;
        }
        quicksort(ary);
        print(ary);
    }
*/

    public static void print(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + ", ");
        }
        System.out.println();
    }
}
