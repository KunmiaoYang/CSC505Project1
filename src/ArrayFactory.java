import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * Created by Kunmiao Yang on 2/1/2018.
 */
abstract public class ArrayFactory {
    private static ArrayFactory randomFactory = ArrayFactory.getRandomFactory();
    private static Comparator<Integer> reverseComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    };

    abstract Integer[] createArray(int size);
    abstract ArrayList<Integer> createArrayList(int size);

    public static ArrayFactory getRandomFactory() {
        return new ArrayFactory() {
            @Override
            Integer[] createArray(int size) {
                Integer[] array = new Integer[size];
                Random random = new Random();
                for(int i = 0; i < size; i++) array[i] = random.nextInt(Integer.MAX_VALUE) + 1;
                return array;
            }

            @Override
            ArrayList<Integer> createArrayList(int size) {
                ArrayList<Integer> array = new ArrayList<>(size);
                Random random = new Random();
                for(int i = 0; i < size; i++) array.add(random.nextInt());
                return array;
            }
        };
    }

    public static ArrayFactory getSortedFactory() {
        return new ArrayFactory() {
            @Override
            Integer[] createArray(int size) {
                Integer[] array = randomFactory.createArray(size);
                Arrays.sort(array);
                return array;
            }

            @Override
            ArrayList<Integer> createArrayList(int size) {
                ArrayList<Integer> array = new ArrayList<>(size);
                for(int i = 1; i <= size; i++) array.add(i);
                return array;
            }
        };
    }

    public static ArrayFactory getReverseFactory() {
        return new ArrayFactory() {
            @Override
            Integer[] createArray(int size) {
                Integer[] array = randomFactory.createArray(size);
                Arrays.sort(array, reverseComparator);
                return array;
            }

            @Override
            ArrayList<Integer> createArrayList(int size) {
                ArrayList<Integer> array = new ArrayList<>(size);
                for(int i = size; i > 0; i--) array.add(i);
                return array;
            }
        };
    }
}
