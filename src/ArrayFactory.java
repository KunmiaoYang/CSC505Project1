import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Created by Kunmiao Yang on 2/1/2018.
 */
public class ArrayFactory {
    public static ArrayList<Integer> createSortedArrayList(int size, boolean isIncreasing) {
        ArrayList<Integer> array = new ArrayList<>(size);
        if(isIncreasing) for(int i = 1; i <= size; i++) array.add(i);
        else for(int i = size; i > 0; i--) array.add(i);
        return array;
    }
    public static ArrayList<Integer> createRandomArrayList(int size) {
        ArrayList<Integer> array = new ArrayList<>(size);
        Random random = new Random();
        for(int i = 0; i < size; i++) array.add(random.nextInt());
        return array;
    }
    public static Integer[] createSortedArray(int size, boolean isIncreasing) {
        Integer[] array = new Integer[size];
        if(isIncreasing) for(int i = 0; i < size; i++) array[i] = i + 1;
        else for(int i = 0; i < size; i++) array[i] = size - i;
        return array;
    }
    public static Integer[] createRandomArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for(int i = 0; i < size; i++) array[i] = random.nextInt();
        return array;
    }
}
