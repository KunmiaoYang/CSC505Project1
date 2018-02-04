import java.io.*;
import java.util.*;

/**
 * Abstract sort class
 * Created by Kunmiao Yang on 2/1/2018.
 */
abstract public class Sort {
    public static final String KEY_COMPARISON = "Comparison";       // Comparison key in report
    public static final String KEY_TIME = "Time";                   // Time key in report
    protected RecordComparator comp;                                // Comparator used in the sort

    /**
     * Initialize sort with comparator
     * @param comp Comparator used in the sort
     */
    public Sort(RecordComparator comp) {
        // Initialize sort with comparator
        this.comp = comp;
    }

    /**
     * Sort the array
     * @param array the array to be sorted
     */
    abstract public void sort(ArrayList<Integer> array);

    /**
     * Sort the array
     * @param array the array to be sorted
     */
    abstract public void sort(Integer[] array);


    /**
     * Swap numbers in ArrayList
     * @param array the array to where the 2 numbers are in
     * @param i the index of the first number
     * @param j the index of the second number
     */
    public static void swap(ArrayList<Integer> array, int i, int j) {
        Integer temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    /**
     * Swap numbers in ArrayList
     * @param array the array to where the 2 numbers are in
     * @param i the index of the first number
     * @param j the index of the second number
     */
    public static void swap(Integer[] array, int i, int j) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Read Array from given input stream
     * @param in the input stream to read the array
     * @return an integer array read from the input stream
     * @throws IOException
     */
    public static Integer[] readArray(InputStream in) throws IOException {
        Integer[] array;

        // Initialize reader
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        // Get the array size
        int n = Integer.parseInt(br.readLine().split("\\s+")[1]);

        // Initialize array
        array = new Integer[n];

        // Read array number from standard input
        for(int i = 0; i < n; i++) array[i] = Integer.parseInt(br.readLine());

        return array;
    }

    /**
     * Standard test framework
     * @param array the array to be sorted
     * @param sort the sort object to be used
     * @throws IOException
     */
    public static void standardTest(Integer[] array, Sort sort) throws IOException {
        // Sort array and get the report
        Map<String, Number> report = sort.testSort(array);

        // Output the sorted array to standard output
        for(Integer i: array) System.out.println(i);

        // Output the statistics report to error output
        System.err.println("runtime," + report.get(KEY_TIME));
        System.err.println("comparisons," + report.get(KEY_COMPARISON));
    }

    /**
     * Sort test with time and comparison statistics
     * @param array the array to be sorted
     * @return the report of the comparison statistics
     */
    public Map<String, Number> testSort(Integer[] array) {
        // Initialize the report
        Map<String, Number> report = new HashMap<>();

        // Initialize the time variables
        long startTime, endTime;

        // Set the comparator counter to 0
        comp.setCount(0);

        // Record start time
        startTime=System.currentTimeMillis();

        // Sort array
        sort(array);

        // Record end time
        endTime=System.currentTimeMillis();

        // Put statistics into report and return
        report.put(KEY_TIME, endTime - startTime);
        report.put(KEY_COMPARISON, comp.getCount());
        return report;
    }

//    protected Map<String, Number> testList(Collection<ArrayList<Integer>> arrayList) {
//        Map<String, Number> report = new HashMap<>();
//        long startTime, endTime;
//        comp.setCount(0);
//        startTime=System.currentTimeMillis();
//        for(ArrayList<Integer> array: arrayList) sort(array);
//        endTime=System.currentTimeMillis();
//        report.put("Average time", (endTime - startTime) / (double) arrayList.size());
//        report.put("Average comparison", comp.getCount() / (double) arrayList.size());
//        return report;
//    }

//    protected Map<String, Number> testArray(Collection<Integer[]> arrayList) {
//        Map<String, Number> report = new HashMap<>();
//        long startTime, endTime;
//        comp.setCount(0);
//        System.out.println("Begin test");
//        startTime=System.currentTimeMillis();
//        for(Integer[] array: arrayList) sort(array);
//        endTime=System.currentTimeMillis();
//        System.out.println("End test");
//        report.put("Average time", (endTime - startTime) / (double) arrayList.size());
//        report.put("Average comparison", comp.getCount() / (double) arrayList.size());
//        return report;
//    }

//    protected void testAllList(int[] arraySizes) throws Throwable {
//        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>(SAMPLE_SIZE);
//
//        // Test random array
//        System.out.println("------------------------ Random array test ------------------------");
//        for(int arraySize: arraySizes) {
//            arrayList.clear();
//            finalize();
//            for (int i = 0; i < SAMPLE_SIZE; i++)
//                arrayList.add(ArrayFactory.createRandomArrayList(arraySize));
//            Map<String, Number> report = testList(arrayList);
//            System.out.println("Array Size = " + arraySize);
//            System.out.println(report);
//        }
//
//        // Test sorted array
//        System.out.println("------------------------ Sorted array test ------------------------");
//        for(int arraySize: arraySizes) {
//            arrayList.clear();
//            finalize();
//            for (int i = 0; i < SAMPLE_SIZE; i++)
//                arrayList.add(ArrayFactory.createSortedArrayList(arraySize, true));
//            Map<String, Number> report = testList(arrayList);
//            System.out.println("Array Size = " + arraySize);
//            System.out.println(report);
//        }
//
//        // Test sorted array
//        System.out.println("------------------------ Reverse array test ------------------------");
//        for(int arraySize: arraySizes) {
//            arrayList.clear();
//            finalize();
//            for (int i = 0; i < SAMPLE_SIZE; i++)
//                arrayList.add(ArrayFactory.createSortedArrayList(arraySize, false));
//            Map<String, Number> report = testList(arrayList);
//            System.out.println("Array Size = " + arraySize);
//            System.out.println(report);
//        }
//    }
}
