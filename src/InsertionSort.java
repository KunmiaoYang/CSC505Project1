import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * Created by Kunmiao Yang on 2/1/2018.
 */
public class InsertionSort extends Sort {
    public InsertionSort(RecordComparator comp) {
        super(comp);
    }

    @Override
    public void sort(ArrayList<Integer> array) {
        int n = array.size();
        for(int i = 1; i < n; i++) {
            // Get the current number
            int cur = array.get(i);
            // Declare the integer to record the first position where the number is less or equal than current number
            int j;
            // Loop all the numbers before current number that are larger than current number
            for(j = i - 1; j >= 0 && comp.compare(array.get(j), cur) > 0; j--) {
                // Move all the numbers to the next position
                array.set(j + 1, array.get(j));
            }
            // Put the current number next to the first position where the number is less or equal than current number
            array.set(j + 1, cur);
        }
    }

    @Override
    public void sort(Integer[] array) {
        int n = array.length;
        for(int i = 1; i < n; i++) {
            // Get the current number
            int cur = array[i];
            // Declare the integer to record the first position where the number is less or equal than current number
            int j;
            // Loop all the numbers before current number that are larger than current number
            for(j = i - 1; j >= 0 && comp.compare(array[j], cur) > 0; j--) {
                // Move all the numbers to the next position
                array[j + 1] = array[j];
            }
            // Put the current number next to the first position where the number is less or equal than current number
            array[j + 1] = cur;
        }
    }

    public static void main(String[] args) throws Throwable {
        standardTest(new InsertionSort(new RecordComparator()));
    }
}
