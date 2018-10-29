package unaryPredicate;

import Exceptions.IndexingError;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-29
 */
public abstract class CountingUnaryPredicate <T extends Comparable<? super T>>
        implements UnaryPredicateCount<T> {
    @Override
    public int numberSatisfying(T[] array) throws IndexingError {
        int satisfied = 0; // initialize
        for (Comparable element : array) { // for each element
            if (test((T) element)) { // check if it satisfies
                satisfied++; // incrmeent if yes
            }
        }
        return satisfied; // total amount of passed test method elements
    }

}
