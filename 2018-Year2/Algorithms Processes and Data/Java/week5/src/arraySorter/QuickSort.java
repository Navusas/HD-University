package arraySorter;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-11-11
 */
public class QuickSort<T extends Comparable<? super T>> implements ArraySort<T> {

    private T[] array;
    private int length;

    @Override
    public T[] sort(T[] array) {
        this.array = array;
        if (array == null || array.length == 0) {
            throw new IndexOutOfBoundsException("Array haven't been initialized");
        }
        length = array.length;
        quickSort(0, length - 1); // recursively quick sort the array
        return array;
    }

    /**
     * Recursive Quick Sort method to sort the array using pivot as middle element
     *
     * @param lowerIndex first index to find bigger value then pivot
     * @param higherIndex last index to find lower value then pivot
     */
    private void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        T pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i].compareTo(pivot) < 0) {
                i++;
            }
            while (array[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    /**
     * Changes to values within array at given indexes as parameters
     *
     * @param i first element to swap
     * @param j second element to swap
     */
    private void exchangeNumbers(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
