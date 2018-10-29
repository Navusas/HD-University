package otherObjects;

/**
 *  Generates Incrementing integers array of desired size
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

public class ArrayGenerator {

    /**
     * Create an integers array of desired size
     *
     * @param size  Desired size of integers array to be generated
     * @return      Generated incrementing integers array of given size
     */
    public Integer[] generateInt(int size) {
        Integer[] array = new Integer[size]; // create integers array instance of desired size
        // Assign values for every array element in increment order
        for(int i = 0; i < size; i++) {
            array[i] = i;
        }

        return array; // returns generated increment integers array
    }
    /**
     * Create an even array of desired size
     *
     * @param size  Desired size of integers array to be generated
     * @return      Generated even integers array of given size
     */
    public Integer[] generateEven(int size) {
        Integer[] array = new Integer[size]; // create integers array instance of desired size
        int initializer = 0;

        // Assign values for every array element
        for(int i = 0; i < size*2; i+=2) {
            array[initializer] = i;
            initializer++;
        }

        return array; // returns generated even integers array
    }

    /**
     * Create an ODD numbers array of desired size
     *
     * @param size  Desired size of integers array to be generated
     * @return      Generated odd integers array of given size
     */
    public Integer[] generateOdd(int size) {
        Integer[] array = new Integer[size]; // create integers array instance of desired size
        int initializer = 0;

        // Assign values for every array element
        for(int i = 1; i < size*2; i+=2) {
            array[initializer] = i;
            initializer++;
        }

        return array; // returns generated odd integers array
    }
}
