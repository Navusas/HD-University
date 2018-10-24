package genericMethods;

import Exceptions.IndexingError;

/**
 *  Finds biggest possible element within given generic objects array and rnage (from,to)
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

public class MaxValue {

    /**
     * Strategy:
     *  1. Checks if indexes are valid
     *  2. Compares each item in array from and to given indexes
     *  3. Returns largest element
     *
     * @param array generic objects array where to look for maximum value
     * @param from position from where to look
     * @param to position where finish looking
     * @param <T> any generic COMPARABLE object
     * @return  maximum value found within specified generic array range
     * @throws IndexingError if index is less then 0, bigger then size, or 'from' bigger then 'to'
     */
    static <T extends Comparable<? super T>> T getMax(T[] array, int from, int to) throws IndexingError {

        int[] indexes = {to,from}; // create temporary index array for testing

        /*
           Validates indexes
          Throws IndexingError.class if validation failed
          @see IndexingError.class
         * */
        if(!indexOkey(indexes, array.length)) // if index is less then 0 or bigger then given array length
            throw new IndexingError("Index is invalid!");
        else if (indexIncompatible(from,to)) // if index 1 i sbigger then index 2
            throw new IndexingError("Index 1 must be lower or equal compared to index2");

        int maxIndex = from; // set the current max value
        for(int i = from; i < to; i++) {
            if(array[maxIndex].compareTo(array[i]) < 0) { // compare each element with maximum.
                maxIndex = i; // if bigger value been find, update maxIndex instance
            }
        }
        return array[maxIndex];
        }

    /**
     * Validates indexes by checking if they vre within valid range
     *
     * @param indexes array of indexes to be checked
     * @param maxPossibleIndex size of biggest possible index element
     * @return true if indexes are valid
     */
    private static boolean indexOkey(int[] indexes, int maxPossibleIndex) {
        boolean okey = true;
        for(int index : indexes) {
            if( index < 0 || index > maxPossibleIndex) {
                okey = false;
                break;
            }
        }
        return okey;
    }

    /**
     * Validating indexes by checking if 1 index is bigger then other one
     * @param index1 first index
     * @param index2 second index
     * @return true if index1 is bigger then index 2 (INCOMPATIBLE )
     */
    private static boolean indexIncompatible(int index1, int index2) {
        return index1 > index2;
    }

}
