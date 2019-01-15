package SingleLinkedList.scope;

import java.util.Random;
import java.util.Set;

/**
 * Implements {@link SingleLinkedList.scope.Scope}, when the set (or <i>alphabet</i>) of possible values is specified
 * by a {@link Set}.
 *
 * @author Hugh Osborne
 * @version October 2018
 */

public class AlphabetScope<T> implements Scope<T> {

    // the alphabet (set of possible values) of this SingleLinkedList.scope
    private Set<T> alphabet;

    private Random random = new Random();

    /**
     *
     * @param alphabet the alphabet of this SingleLinkedList.scope.
     */
    public AlphabetScope(Set<T> alphabet) {
        this.alphabet = alphabet;
    }

    /**
     * Tests whether the given value within the SingleLinkedList.scope of this Scope.
     * I.e., is it in the alphabet of this SingleLinkedList.scope.
     *
     * @param value the value to be checked.
     * @return true iff the value is within the SingleLinkedList.scope of this Scope.
     */
    @Override
    public boolean contains(T value) {
        return alphabet.contains(value);
    }

    /**
     * Get a value within the SingleLinkedList.scope of this Scope.
     *
     * @return a random value from this SingleLinkedList.scope's alphabet.
     */
    @Override
    public T getValue() {
        return alphabet.stream()
                .skip(random.nextInt(alphabet.size()))
                .findFirst().get();
    }

    /**
     * Get the size of this SingleLinkedList.scope.
     * The size is the number of elements in the SingleLinkedList.scope's alphabet.
     *
     * @return the size of this SingleLinkedList.scope's alphabet..
     */
    @Override
    public int size() {
        return alphabet.size();
    }
}
