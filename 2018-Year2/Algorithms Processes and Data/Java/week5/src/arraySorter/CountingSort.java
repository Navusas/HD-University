package arraySorter;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-11-12
 */
public class CountingSort<T extends Comparable<? super T>> implements ArraySort<T> {
    @Override
    public T[] sort(T[] array) {
        T[] output = array.clone();
        Map<T,Integer> counts = new TreeMap<>();

        for(T t: array) {
            counts.merge(t,1,Integer::sum);
        }
        int i = 0;
        for(Map.Entry<T,Integer> entry : counts.entrySet()) {
            for(int j = 0; j < entry.getValue(); j++)
                output[i++] = entry.getKey();
        }
        return output;
    }
}
