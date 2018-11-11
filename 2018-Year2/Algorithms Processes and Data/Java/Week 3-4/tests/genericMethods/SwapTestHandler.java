package genericMethods;

import Exceptions.IndexingError;
import otherObjects.ArrayGenerator;

import static genericMethods.Swap.swapPosition;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  SwapTest class handler for avoiding duplicates
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

public abstract class SwapTestHandler extends ArrayGenerator {

    /**
     * Swap the objects at given positions as parameters [index1] and [index2] in given objects array
     *
     * @param object Original generic object array
     * @param index1 First index determines first object position in array to swap
     * @param index2 Second index determines second object position in array to swap
     * @param <T> generic object array
     * @throws IndexingError if given indexes are less then 0 or bigger then given object size
     */
    <T> void testArray(T[] object, int index1, int index2) throws IndexingError {

        T[] original = object.clone(); // clones the given object (otherwise the object shares references in memory)

         /*
         * Swaps the element in array using Swap.java class*
         *
         **/
        swapPosition(original,index1,index2);

        // Using JUnit5 test if the elements been swapped comparing to original object itself
        assertArrayEquals(swapPosition(object,index1,index2),original);
    }
}
