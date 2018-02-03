import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * Created by Kunmiao Yang on 2/2/2018.
 */
public class JavaSort extends Sort {
    public JavaSort(RecordComparator comp) {
        super(comp);
    }

    @Override
    public void sort(ArrayList<Integer> array) {
        Collections.sort(array, comp);
    }

    @Override
    public void sort(Integer[] array) {
        Arrays.sort(array, comp);
    }

    public static void main(String[] args) throws Throwable {
        standardTest(new JavaSort(new RecordComparator()));
    }
}
