import java.util.Comparator;

/**
 * A comparator with comparison counter
 * Created by Kunmiao Yang on 2/1/2018.
 */
public class RecordComparator implements Comparator<Integer> {
    private long count = 0;     // Comparison count

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        // Count comparison
        count++;

        // I don't use o1-o2 so as to avoid integer overflow
        return o1 == o2 ? 0 : o1 < o2 ? -1 : 1;
    }

    /**
     * Comparison count getter
     * @return Comparison count
     */
    public long getCount() {
        return count;
    }

    /**
     * Comparison count setter
     * @param count Comparison count
     */
    public void setCount(long count) {
        this.count = count;
    }
}
