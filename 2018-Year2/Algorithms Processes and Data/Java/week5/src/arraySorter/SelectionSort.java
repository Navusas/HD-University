package arraySorter;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-11-11
 */
public class SelectionSort<T extends Comparable<? super T>> implements ArraySort<T> {
    /**
     * Sorts array using Selection Sort Algorithm
     *
     * @param array the array to be sorted.
     * @return sorted array
     */
    @Override
    public T[] sort(T[] array) {

        for(int i = 0; i < array.length-1; i++) {
            int minimum = i; // take every entry index
            for(int m = i + 1; m < array.length; m++) { // compare it to the rest of the array
                if(array[minimum].compareTo(array[m]) > 0) { // if smaller value found
                    minimum = m; // assign new index to our current index
                }
            }
            if(minimum != i) { // if its not the end
                // swap current value with lower found
                T swapElement = array[i];
                array[i] = array[minimum];
                array[minimum] = swapElement;
            }
        }
        return array; // return sorted array
    }
}
