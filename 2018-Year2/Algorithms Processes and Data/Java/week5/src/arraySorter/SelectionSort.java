package arraySorter;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-11-11
 */
public class SelectionSort<T extends Comparable<? super T>> implements ArraySort<T> {
    @Override
    public T[] sort(T[] array) {

        for(int i = 0; i < array.length-1; i++) {
            int minimum = i;
            for(int m = i + 1; m < array.length; m++) {
                if(array[minimum].compareTo(array[m]) > 0) {
                    minimum = m;
                }
            }
            if(minimum != i) {
                T swapElement = array[i];
                array[i] = array[minimum];
                array[minimum] = swapElement;
            }
        }
        return array;
    }
}
