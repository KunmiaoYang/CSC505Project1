import java.io.*;
import java.util.*;

/**
 *
 * Created by Kunmiao Yang on 2/1/2018.
 */
abstract public class Sort {
    private static final int SAMPLE_SIZE = 10;
    private static final String KEY_COMPARISON = "Comparison";
    private static final String KEY_TIME = "Time";
    protected RecordComparator comp;

    public Sort(RecordComparator comp) {
        this.comp = comp;
    }

    abstract public void sort(ArrayList<Integer> array);
    abstract public void sort(Integer[] array);

    public static void swap(ArrayList<Integer> array, int i, int j) {
        Integer temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    public static void swap(Integer[] array, int i, int j) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static Integer[] readArray(InputStream in) throws IOException {
        Integer[] array;
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int n = Integer.parseInt(br.readLine().split("\\s+")[1]);
        array = new Integer[n];
        for(int i = 0; i < n; i++) array[i] = Integer.parseInt(br.readLine());
        return array;
    }

    public static void standardTest(Sort sort) throws IOException {
        Integer[] array = readArray(System.in);
        Map<String, Number> report = sort.testSort(array);
        for(Integer i: array) System.out.println(i);
        System.err.println("runtime," + report.get(KEY_TIME));
        System.err.println("comparisons," + report.get(KEY_COMPARISON));
    }

    public Map<String, Number> testSort(Integer[] array) {
        Map<String, Number> report = new HashMap<>();
        long startTime, endTime;
        comp.setCount(0);
        startTime=System.currentTimeMillis();
        sort(array);
        endTime=System.currentTimeMillis();
        report.put(KEY_TIME, endTime - startTime);
        report.put(KEY_COMPARISON, comp.getCount());
        return report;
    }

    protected Map<String, Number> testList(Collection<ArrayList<Integer>> arrayList) {
        Map<String, Number> report = new HashMap<>();
        long startTime, endTime;
        comp.setCount(0);
        startTime=System.currentTimeMillis();
        for(ArrayList<Integer> array: arrayList) sort(array);
        endTime=System.currentTimeMillis();
        report.put("Average time", (endTime - startTime) / (double) arrayList.size());
        report.put("Average comparison", comp.getCount() / (double) arrayList.size());
        return report;
    }

    protected Map<String, Number> testArray(Collection<Integer[]> arrayList) {
        Map<String, Number> report = new HashMap<>();
        long startTime, endTime;
        comp.setCount(0);
        System.out.println("Begin test");
        startTime=System.currentTimeMillis();
        for(Integer[] array: arrayList) sort(array);
        endTime=System.currentTimeMillis();
        System.out.println("End test");
        report.put("Average time", (endTime - startTime) / (double) arrayList.size());
        report.put("Average comparison", comp.getCount() / (double) arrayList.size());
        return report;
    }

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

    protected static void testAllArray(Sort[] sorts, int[] arraySizes, ArrayFactory arrayFactory, PrintStream out, String title) throws Throwable {
        // Test random array
        System.out.println(title);
        out.println(title);
        for(int arraySize: arraySizes) {
            // Log on console
            System.out.print("Array Size = " + arraySize + "\tX");

            // Initialize reports data structure
            ArrayList<Map<String, List<Number>>> reports = new ArrayList<>(sorts.length);
            for(int i = 0; i < sorts.length; i++) {
                Map<String, List<Number>> report = new HashMap<>();
                report.put(KEY_TIME, new ArrayList<>(SAMPLE_SIZE));
                report.put(KEY_COMPARISON, new ArrayList<>(SAMPLE_SIZE));
                reports.add(report);
            }

            // Test the same sample on each sort algorithm
            for (int i = 0; i < SAMPLE_SIZE; i++) {
                Integer[][] arrays = new Integer[sorts.length][];
                arrays[0] = arrayFactory.createArray(arraySize);
                for(int j = 1; j < sorts.length; j++) arrays[j] = Arrays.copyOf(arrays[0], arraySize);
                for(int j = 0; j < sorts.length; j++) {
                    Map<String, Number> report = sorts[j].testSort(arrays[j]);
                    reports.get(j).get(KEY_TIME).add(report.get(KEY_TIME));
                    reports.get(j).get(KEY_COMPARISON).add(report.get(KEY_COMPARISON));
                    System.out.print('.');
                }
                System.out.print('X');
            }
            System.out.println();

            // Output report
            out.println("***************** Array Size = " + arraySize + " *****************");
            for(int i = 0; i < sorts.length; i++) {
                Map<String, List<Number>> report = reports.get(i);
                List<Number> times = report.get(KEY_TIME), comparisons = report.get(KEY_COMPARISON);
                long totalTime = 0, totalComparison = 0;
                for(Number comparison: comparisons) totalComparison += (long)comparison;
                for(Number time: times) totalTime += (long)time;
                out.println(sorts[i].getClass().getName() + " report:");
                out.println("\tTimes:\t" + times);
                out.println("\tAverage Time:\t" + totalTime/(float)SAMPLE_SIZE);
                out.println("\tComparisons:\t" + comparisons);
                out.println("\tAverage Comparison:\t" + totalComparison/(float)SAMPLE_SIZE);
            }
            out.flush();
        }
    }
}
