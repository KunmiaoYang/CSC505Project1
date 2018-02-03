import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static final int[] ARRAY_SIZES = {100000, 200000, 400000, 800000, 1600000, 3200000, 6400000, 12800000};
//    private static final int[] ARRAY_SIZES = {100000, 200000, 400000};
    private static final int[] ARRAY_SIZES_FOR_INSERTION_SORT = {3125, 6250, 12500, 25000, 50000, 100000, 200000, 400000};
    public static void testArrayListCorrection(Sort sort) {
        ArrayFactory sortedFactory = ArrayFactory.getSortedFactory(),
                reverseFactory = ArrayFactory.getReverseFactory(),
                randomFactory = ArrayFactory.getRandomFactory();
        ArrayList<Integer> array = reverseFactory.createArrayList(5);
        System.out.println("Initial array = " + array);
        RecordComparator comp = sort.comp;
        sort.sort(array);

        System.out.println("Result = " + array);
        System.out.println("Comparison = " + comp.getCount());
    }

    public static void testArrayCorrection(Sort sort) {
        ArrayFactory sortedFactory = ArrayFactory.getSortedFactory(),
                reverseFactory = ArrayFactory.getReverseFactory(),
                randomFactory = ArrayFactory.getRandomFactory();
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
        Sort insertSort = new InsertionSort(comp), mergeSort = new MergeSort(comp),
                heapSort = new HeapSort(comp), javaSort = new JavaSort(comp),
                sortGroup1[] = {mergeSort, heapSort, javaSort},
                sortGroup2[] = {mergeSort, insertSort},
                tempGroup[] = {mergeSort};

//        testArrayCorrection(new MergeSort(comp));
//        new MergeSort(comp).testAllArray(sortGroup1, ARRAY_SIZES, ArrayFactory.getRandomFactory(), ps, "------------------------------- Random array test -------------------------------");
        new MergeSort(comp).testAllArray(sortGroup1, ARRAY_SIZES, ArrayFactory.getSortedFactory(), ps, "------------------------------- Sorted array test -------------------------------");
//        new MergeSort(comp).testAllArray(sortGroup1, ARRAY_SIZES, ArrayFactory.getReverseFactory(), ps, "------------------------------- Reverse array test -------------------------------");
    }
}
