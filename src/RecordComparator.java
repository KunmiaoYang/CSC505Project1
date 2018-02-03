import java.util.Comparator;

/**
 *
 * Created by Kunmiao Yang on 2/1/2018.
 */
public class RecordComparator implements Comparator<Integer> {
    private long count = 0;
    @Override
    public int compare(Integer o1, Integer o2) {
        count++;
        return o1 == o2 ? 0 : o1 < o2 ? -1 : 1;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
