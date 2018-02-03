import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static final int[] ARRAY_SIZES = {100000, 200000, 400000, 800000, 1600000, 3200000, 6400000, 12800000};
    private static final int[] ARRAY_SIZES_FOR_INSERTION_SORT = {3125, 6250, 12500, 25000, 50000, 100000, 200000, 400000};
    public static void testArrayListCorrection(Sort sort) {
        ArrayList<Integer> array = ArrayFactory.createSortedArrayList(5, false);
        System.out.println("Initial array = " + array);
        RecordComparator comp = sort.comp;
        sort.sort(array);

        System.out.println("Result = " + array);
        System.out.println("Comparison = " + comp.getCount());
    }

    public static void testArrayCorrection(Sort sort) {
//        Integer[] array = ArrayFactory.createSortedArray(9, true);
        Integer[] array = {5,1,2,4,3,6,9,7,8};
        System.out.println("Initial array = " + Arrays.toString(array));
        RecordComparator comp = sort.comp;
        sort.sort(array);

        System.out.println("Result = " + Arrays.toString(array));
        System.out.println("Comparison = " + comp.getCount());
    }

    public static void main(String[] args) throws Throwable {
        PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("result.txt")));
        RecordComparator comp = new RecordComparator();
//        testArrayCorrection(new MergeSort(comp));
        ps.println("*************************** Merge sort test ***************************");
        System.out.println("*************************** Merge sort test ***************************");
        new MergeSort(comp).testAllArray(ARRAY_SIZES, ps);
//        ps.println("*************************** Heap sort test ***************************");
//        System.out.println("*************************** Heap sort test ***************************");
//        new HeapSort(comp).testAllArray(ARRAY_SIZES, ps);
//        ps.println("*************************** Java sort test ***************************");
//        System.out.println("*************************** Java sort test ***************************");
//        new JavaSort(comp).testAllArray(ARRAY_SIZES, ps);
//        ps.println("*************************** Insertion sort test ***************************");
//        System.out.println("*************************** Insertion sort test ***************************");
//        new InsertionSort(comp).testAllArray(ARRAY_SIZES_FOR_INSERTION_SORT, ps);
    }
}
