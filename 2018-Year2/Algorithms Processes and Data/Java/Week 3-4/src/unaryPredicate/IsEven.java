package unaryPredicate;

/**
 * An example of a unary predicate.
 *
 * IsEven implements a unary predicate over Integers.
 *
 * The test method therefore takes an Integer as its parameter, and returns true
 * iff the parameter is an even number.
 *
 * @author Hugh Osborne
 * @version October 2018
 */

public class IsEven extends CountingUnaryPredicate<Integer> {

    /**
     * Test whether a number is even.
     *
     * @param n the number t be tested
     * @return true iff n is even
     */
    @Override
    public boolean test(Integer n) {
        return n % 2 == 0;
    }

    /**
     *  Count how much elements satisfy test method
     *
     * @param array An array of objects of the type tested by this predicate's test
     * @return  number of elements satisfied the test method
     */
}
