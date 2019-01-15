package SingleLinkedList.scope;

/**
 * A SingleLinkedList.scope represents a set of values - e.g. an alphabet of characters, or a range of integers.
 *
 * @param <T> the type scoped by this Scope
 */
public interface Scope<T> {

    /**
     * Is the given value within the SingleLinkedList.scope of this Scope?
     * 
     * @param value the value to be checked.
     * @return true iff the value is within the SingleLinkedList.scope of this Scope.
     */
    public boolean contains(T value);

    /**
     * Get a value within the SingleLinkedList.scope of this Scope.
     * 
     * @return a value within this SingleLinkedList.scope.
     */
    public T getValue();

    /**
     * How many values are there within this SingleLinkedList.scope?
     * 
     * @return the number of possible distinct values within this SingleLinkedList.scope.
     */
    public int size();
}
