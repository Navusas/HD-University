package otherObjects;

/**
 * Generates random integers array of required size
 * there is default values from and to which can be changed by using differ method
 *
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-29
 */
public class RandomArrayGenerator {

    // static values in what range integers will be generated
    private static int FROM = -500000;
    private static int TO = 500000;


    /**
     * Generate array of desired size in DEFAULT range
     *
     * @param size  desired array sixe
     * @return  array with randomly generated integer values
     */
    public int[] generateArray(int size) {
        return new java.util.Random().ints(size,FROM,TO).toArray();
    }

    /**
     *  Generate array of given size between specified range
     *
     * @param size   array size
     * @param from  minimum integer value to be generated
     * @param to    maximum integer value to be generated
     * @return      generated array of given size between specified range
     */
    public int[] generateArray(int size,int from, int to) {
        return new java.util.Random().ints(size,from,to).toArray();
    }
}
