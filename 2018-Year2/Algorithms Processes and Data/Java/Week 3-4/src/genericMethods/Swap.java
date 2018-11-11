package genericMethods;

import Exceptions.IndexingError;
import org.jetbrains.annotations.Contract;

/**
 *  Swaps the objects into generic objects array at positions with given indexes
 *
 * @author          Domantas Giedraitis (student id: u1757704)
 * @version         1
 * @since           24/10/2018
 */

public class Swap {

    /**
     * Swap the objects at given positions as parameters [index1] and [index2] in given generic object array
     *
     * @param array Generic array of objects where elements at given indexes will be swapped
     * @param index1 First index determines first object position in array to swap
     * @param index2 Second index determines second object position in array to swap
     * @param <T> Generic object to be used and returned by this method
     * @return Given object array with swapped elements in given indexes.
     * @throws IndexingError    if given indexes are less then 0 or bigger then given object size
     */
    @Contract("_, _, _ -> param1")
    static <T> T[] swapPosition(T[] array, int index1, int index2) throws IndexingError{
        // check if indexes are not out of bounds and legal
        if(index1 < 0 || index2 < 0 || index1 > array.length || index2 > array.length) {
            throw new IndexingError("Index Out of Bounds"); // throw IndexingError if indexes are illegal
        }
        else {
            T tempObject = array[index1];   // Hold the first element
            array[index1] = array[index2];  // Put second element into first position
            array[index2] = tempObject;     // Put first element into second position
        }

        return array;   //returns array with swapped objects in it.
    }
}

