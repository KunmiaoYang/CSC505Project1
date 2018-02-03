import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Main {
    private static final int[] ARRAY_SIZES = {100000, 200000, 400000, 800000, 1600000, 3200000, 6400000, 12800000};
//    private static final int[] ARRAY_SIZES = {100000, 200000, 400000};
    private static final int[] ARRAY_SIZES_FOR_INSERTION_SORT = {3125, 6250, 12500, 25000, 50000, 100000, 200000, 400000};
    private static final int SAMPLE_SIZE = 10;
    private static final String SEPARATOR_MAIN = " -------------------------------------- ";
    private static final String SEPARATOR_SUB = " ************************************* ";
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
        array = sortedFactory.createArray(10);
        System.out.println("Initial array = " + Arrays.toString(array));
        RecordComparator comp = sort.comp;
        sort.sort(array);

        System.out.println("Result = " + Arrays.toString(array));
        System.out.println("Comparison = " + comp.getCount());
    }

    protected static void testAllArray(Sort[] sorts, int[] arraySizes, ArrayFactory arrayFactory, PrintStream out, String title) throws Throwable {
        // Initialize summaries
        ArrayList<Map<String, List<Number>>> summaries = new ArrayList<>(sorts.length);
        for(Sort sort: sorts) {
            Map<String, List<Number>> summary = new HashMap<>();
            summary.put(Sort.KEY_TIME, new ArrayList<>(arraySizes.length));
            summary.put(Sort.KEY_COMPARISON, new ArrayList<>(arraySizes.length));
            summaries.add(summary);
        }

        System.out.println(title);
        out.println(title);
        for(int arraySize: arraySizes) {
            // Log on console
            System.out.print("Array Size = " + arraySize + "\tO");

            // Initialize reports data structure
            ArrayList<Map<String, List<Number>>> reports = new ArrayList<>(sorts.length);
            for(int i = 0; i < sorts.length; i++) {
                Map<String, List<Number>> report = new HashMap<>();
                report.put(Sort.KEY_TIME, new ArrayList<>(SAMPLE_SIZE));
                report.put(Sort.KEY_COMPARISON, new ArrayList<>(SAMPLE_SIZE));
                reports.add(report);
            }

            // Test the same sample on each sort algorithm
            for (int i = 0; i < SAMPLE_SIZE; i++) {
                Integer[][] arrays = new Integer[sorts.length][];
                arrays[0] = arrayFactory.createArray(arraySize);
                for(int j = 1; j < sorts.length; j++) arrays[j] = Arrays.copyOf(arrays[0], arraySize);
                for(int j = 0; j < sorts.length; j++) {
                    Map<String, Number> report = sorts[j].testSort(arrays[j]);
                    reports.get(j).get(Sort.KEY_TIME).add(report.get(Sort.KEY_TIME));
                    reports.get(j).get(Sort.KEY_COMPARISON).add(report.get(Sort.KEY_COMPARISON));
                    System.out.print('.');
                }
                System.out.print('X');
            }
            System.out.println();

            // Output report
            out.println(SEPARATOR_SUB + "Array Size = " + arraySize + SEPARATOR_SUB);
            for(int i = 0; i < sorts.length; i++) {
                Map<String, List<Number>> report = reports.get(i);
                List<Number> times = report.get(Sort.KEY_TIME), comparisons = report.get(Sort.KEY_COMPARISON);
                long totalTime = 0, totalComparison = 0;
                for(Number comparison: comparisons) totalComparison += (long)comparison;
                for(Number time: times) totalTime += (long)time;
                double avgTime = totalTime/(double)SAMPLE_SIZE, avgComparison = totalComparison/(double)SAMPLE_SIZE;
                summaries.get(i).get(Sort.KEY_TIME).add(avgTime);
                summaries.get(i).get(Sort.KEY_COMPARISON).add(avgComparison);

                out.println(sorts[i].getClass().getName() + " report:");
                out.println("\tTimes=\t" + times);
                out.println("\tAverageTime=\t" + avgTime);
                out.println("\tComparisons=\t" + comparisons);
                out.println("\tAverageComparison=\t" + avgComparison);
            }
            out.flush();
        }

        // Output Summary
        out.println(SEPARATOR_SUB + "Summary " + SEPARATOR_SUB);
        out.println("\tArraySize=\t" + Arrays.toString(arraySizes));
        for(int i = 0; i < sorts.length; i++) {
            Map<String, List<Number>> summary = summaries.get(i);
            out.println(sorts[i].getClass().getName() + " summary:");
            out.println("\tAverageTime=\t" + summary.get(Sort.KEY_TIME));
            out.println("\tAverageComparison=\t" + summary.get(Sort.KEY_COMPARISON));
        }
        out.flush();
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
        testAllArray(sortGroup1, ARRAY_SIZES, ArrayFactory.getRandomFactory(), ps, SEPARATOR_MAIN + "Random array test" + SEPARATOR_MAIN);
        testAllArray(sortGroup1, ARRAY_SIZES, ArrayFactory.getSortedFactory(), ps, SEPARATOR_MAIN + "Sorted array test" + SEPARATOR_MAIN);
        testAllArray(sortGroup1, ARRAY_SIZES, ArrayFactory.getReverseFactory(), ps, SEPARATOR_MAIN + "Reverse array test" + SEPARATOR_MAIN);
    }
}
