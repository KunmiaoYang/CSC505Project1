import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Kunmiao Yang on 2/1/2018.
 */
abstract public class Sort {
    private static final int SAMPLE_SIZE = 10;
//    private static final int[] arraySizes = {100000, 200000, 400000, 800000, 1600000, 3200000, 6400000, 12800000};
//    private static final int[] arraySizes = {1000, 2000, 4000};
//    private static final int[] arraySizes = {6400000, 12800000};
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
        long startTime, endTime;
        Integer[] array = readArray(System.in);
        sort.comp.setCount(0);
        startTime=System.currentTimeMillis();
        sort.sort(array);
        endTime=System.currentTimeMillis();
        for(Integer i: array) System.out.println(i);
        System.err.println("runtime," + (endTime - startTime));
        System.err.println("comparisons," + sort.comp.getCount());
    }

    public Map<String, Number> testSort(Integer[] array) {
        Map<String, Number> report = new HashMap<>();
        long startTime, endTime;
        startTime=System.currentTimeMillis();
        sort(array);
        endTime=System.currentTimeMillis();
        report.put("Time", endTime - startTime);
        report.put("Comparison", comp.getCount());
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

    protected void testAllList(int[] arraySizes) throws Throwable {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>(SAMPLE_SIZE);

        // Test random array
        System.out.println("------------------------ Random array test ------------------------");
        for(int arraySize: arraySizes) {
            arrayList.clear();
            finalize();
            for (int i = 0; i < SAMPLE_SIZE; i++)
                arrayList.add(ArrayFactory.createRandomArrayList(arraySize));
            Map<String, Number> report = testList(arrayList);
            System.out.println("Array Size = " + arraySize);
            System.out.println(report);
        }

        // Test sorted array
        System.out.println("------------------------ Sorted array test ------------------------");
        for(int arraySize: arraySizes) {
            arrayList.clear();
            finalize();
            for (int i = 0; i < SAMPLE_SIZE; i++)
                arrayList.add(ArrayFactory.createSortedArrayList(arraySize, true));
            Map<String, Number> report = testList(arrayList);
            System.out.println("Array Size = " + arraySize);
            System.out.println(report);
        }

        // Test sorted array
        System.out.println("------------------------ Reverse array test ------------------------");
        for(int arraySize: arraySizes) {
            arrayList.clear();
            finalize();
            for (int i = 0; i < SAMPLE_SIZE; i++)
                arrayList.add(ArrayFactory.createSortedArrayList(arraySize, false));
            Map<String, Number> report = testList(arrayList);
            System.out.println("Array Size = " + arraySize);
            System.out.println(report);
        }
    }

    protected void testAllArray(int[] arraySizes, PrintStream out) throws Throwable {
        ArrayList<Integer[]> arrayList = new ArrayList<>(SAMPLE_SIZE);

        // Test random array
        out.println("------------------------ Random array test ------------------------");
        for(int arraySize: arraySizes) {
            arrayList.clear();
            finalize();
            System.out.println("Array Size = " + arraySize);
            for (int i = 0; i < SAMPLE_SIZE; i++)
                arrayList.add(ArrayFactory.createRandomArray(arraySize));
            Map<String, Number> report = testArray(arrayList);
            out.println("Array Size = " + arraySize);
            out.println(report);
            out.flush();
        }

        // Test sorted array
        out.println("------------------------ Sorted array test ------------------------");
        for(int arraySize: arraySizes) {
            arrayList.clear();
            finalize();
            System.out.println("Array Size = " + arraySize);
            for (int i = 0; i < SAMPLE_SIZE; i++)
                arrayList.add(ArrayFactory.createSortedArray(arraySize, true));
            Map<String, Number> report = testArray(arrayList);
            out.println("Array Size = " + arraySize);
            out.println(report);
            out.flush();
        }

        // Test sorted array
        out.println("------------------------ Reverse array test ------------------------");
        for(int arraySize: arraySizes) {
            arrayList.clear();
            finalize();
            System.out.println("Array Size = " + arraySize);
            for (int i = 0; i < SAMPLE_SIZE; i++)
                arrayList.add(ArrayFactory.createSortedArray(arraySize, false));
            Map<String, Number> report = testArray(arrayList);
            out.println("Array Size = " + arraySize);
            out.println(report);
            out.flush();
        }
    }
}
