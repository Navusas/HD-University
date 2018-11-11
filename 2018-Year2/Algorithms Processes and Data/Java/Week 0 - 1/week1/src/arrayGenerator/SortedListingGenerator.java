package arrayGenerator;

import searcher.IndexingError;

/**
 * A simple array generator, that generates a sorted array [0,1,2,..,n]
 *
 * @author Hugh Osborne
 * @version September 2018
 */

public class SortedListingGenerator implements ListingGenerator {

    private int[] array;

    /**
     * Create an array of ints of size <tt>size</tt>, and populate it
     * with the values 0..<tt>size</tt>-1.
     *
     * @param size the size of the array to be created
     */
    SortedListingGenerator(int size) {
        if(size < 1) {
            throw new IndexOutOfBoundsException("Array size must be more then 0!");
        }
        array = new int[size]; // create the array
        for (int index = 0; index < array.length; index++) {  // populate it
            array[index] = index;
        }
    }

    /**
     * @return the array created by this ArrayGenerator
     */
    public int[] getArray() {
        return array;
    }

    /**
     * @return the size of the array
     */
    public int getSize() {
        return array.length;
    }
}
