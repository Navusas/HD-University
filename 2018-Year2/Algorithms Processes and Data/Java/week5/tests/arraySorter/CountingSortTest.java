package arraySorter;


/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-11-12
 */
public abstract class CountingSortTest<T extends Comparable<? super T>> extends ArraySortTest<T> {
    @Override
    ArraySort<T> getSorter() {
        return new CountingSort<>();
    }
}