package unaryPredicate;
/**
 * Finds if given elemnet is prime number
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

public class isPrime implements UnaryPredicate<Integer> {
    /**
     * Determine whether given number is prime number
     *
     * @param number integer to inspect if its prime number
     * @return true if given number is prime
     */
    @Override
    public boolean test(Integer number) {

        for(int current = 2; current < number; current++){
            if(number%current ==0) // if number can divide by lower numbers
                return false; // its not a prime number
        }
        return number > 1; // ensure the 1 is not within a range (1 is not prime number(
    }
}
