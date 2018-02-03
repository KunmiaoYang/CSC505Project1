import java.util.ArrayList;

/**
 *
 * Created by Kunmiao Yang on 2/1/2018.
 */
public class HeapSort extends Sort {
    private int end;

    public HeapSort(RecordComparator comp) {
        super(comp);
    }

    @Override
    public void sort(ArrayList<Integer> array) {
        // Initialize the end index position
        end = array.size() - 1;

        // Build heap
        for(int i = (end - 1) / 2; i >= 0; i--) sift(array, i);

        // Sort
        while (end > 0) {
            swap(array, end--, 0);
            sift(array, 0);
        }
    }

    @Override
    public void sort(Integer[] array) {
        // Initialize the end index position
        end = array.length - 1;

        // Build heap
        for(int i = (end - 1) / 2; i >= 0; i--) sift(array, i);

        // Sort
        while (end > 0) {
            swap(array, end--, 0);
            sift(array, 0);
        }
    }

    private void sift(ArrayList<Integer> array, int i) {
        // Calculate the index of left child and assume it is the max of the children
        int max = 2 * i + 1;

        // Return when current node doesn't have child
        if(max > end) return;

        // If right child doesn't exist, the current max would be the larger child
        // else if current max child is less than the right child, use right child as max
        if(max < end && comp.compare(array.get(max), array.get(max + 1)) < 0) max++;

        if(comp.compare(array.get(i), array.get(max)) < 0) {
            // Swap if super node is less than the max of the child
            swap(array, i, max);

            // Continue sift in the next level
            sift(array, max);
        }
    }

    private void sift(Integer[] array, int i) {
        // Calculate the index of left child and assume it is the max of the children
        int max = 2 * i + 1;

        // Return when current node doesn't have child
        if(max > end) return;

        // If right child doesn't exist, the current max would be the larger child
        // else if current max child is less than the right child, use right child as max
        if(max < end && comp.compare(array[max], array[max + 1]) < 0) max++;

        if(comp.compare(array[i], array[max]) < 0) {
            // Swap if super node is less than the max of the child
            swap(array, i, max);

            // Continue sift in the next level
            sift(array, max);
        }
    }

    public static void main(String[] args) throws Throwable {
        standardTest(new HeapSort(new RecordComparator()));
    }
}
