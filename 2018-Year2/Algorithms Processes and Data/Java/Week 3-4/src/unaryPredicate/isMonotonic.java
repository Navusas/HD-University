package unaryPredicate;

import Exceptions.IndexingError;

/**
 * Validates whether given array of comparable objects are increasing/ decreasing
 * or equals during all array
 *
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-24
 *
 */

/*
* Implements UnaryPredicate interface and cast array ob COMPARABLE objects T
* */
public class isMonotonic<T extends Comparable<? super T>> extends CountingUnaryPredicate<T[]> {

    /**

     * @param objectArray   given array of comparable to be checked
     * @return  ture if all elements in array are consistently increasing/ decreasing or are equal
     *          false if their sequence is mixed
     * @throws IndexingError if given array does not contain more then 2 elements
     */
    @Override
    public boolean test(T[] objectArray) throws IndexingError {
        if(objectArray.length < 2) { // check if given array contains more then 2 elements
            throw new IndexingError("Array non-comparable. Not enough items in array"); // otherwise throw exception
        }

        /*
        * -1 - decreasing
        * 0 - equal
        * 1 - increasing
        * */
        int isIncreasing = objectArray[0].compareTo(objectArray[1]); // initialize toi compare the rest of the array

        /*
        * Check if items in array are consistently increasing/ decreasing or equals
        * */
        for(int i=1; i<objectArray.length; i++) {
            if(objectArray[i-1].compareTo(objectArray[i]) != isIncreasing) {
                return false; // when found that array is not in sequence
            }
        }
        return true; // true if no difference within (in)(de)creasing found
    }
}