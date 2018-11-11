/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-11-11
 */
package arraySorter;

public abstract class SelectionSortTest<T extends Comparable<? super T>> extends ArraySortTest<T> {
    @Override
    ArraySort<T> getSorter() {
        return new SelectionSort<T>();
    }
}