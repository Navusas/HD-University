package unaryPredicate;

/**
 * Used to check if given integer is odd number
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

public class IsOdd implements UnaryPredicate<Integer> {
    /**
     *  Check and return true if given integer is odd
     *  return false otherwise
     *
     * @param number integer to check its oddness / evenness
     * @return  true if number is odd
     */
    @Override
    public boolean test(Integer number) {
        return number % 2 != 0;
    }
}
