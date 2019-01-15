package SingleLinkedList;

import SingleLinkedList.generator.ArrayGenerator;
import linkedList.ListAccessError;
import linkedList.SingleLinkList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-14
 */
public abstract class LinkedListTest<T extends Comparable<? super T>> extends ScopedArrayGeneratorTest<T> {

    @Override
    public abstract ArrayGenerator<T> getGenerator();

    private SingleLinkList<T> linkedList = new SingleLinkList<>();
    private T[] array;

    /**
     * Creates required size generic array AND transfers all the elements to SingleLinkList
     *
     * @param size required size of array
     */
    public void createArray(int size) throws ListAccessError {
        if (linkedList != null) {
            linkedList = null;
        } else if (array != null) {
            array = null;
        }
        array = getGenerator().getArray(size);
        this.linkedList = arrayToLinkList((array));
    }

    /**
     * Takes all elements from given array and store each element into SingleLinkList
     *
     * @param array which elements are going to be stored in SingleLinkList tree
     * @return manipulated list
     */

    private SingleLinkList<T> arrayToLinkList(T[] array) throws ListAccessError {
        SingleLinkList<T> newList = new SingleLinkList<>();
        for (int i = 0; i < array.length; i++) {
            newList.add(i, array[i]);
        }
        return newList;
    }

    /**
     * Checks if given value is in SingleLinkList
     *
     * @param value to be checked if contains it
     */
    private void testIfContains(T value) throws ListAccessError {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                index = i;
                break;
            }
        }
        assertEquals(this.linkedList.get(index), array[index]);
    }

    /**
     * Adds the first ( index 0 ) element to a SingleLinkList required amount of times
     *
     * @param size how many times to add the value
     */
    private void addToList(int size) throws ListAccessError {
        for (int i = 0; i < size; i++) {
            linkedList.add(linkedList.getSize(), array[0]);
        }
        assertEquals(linkedList.getSize(), array.length + size);
    }

    @Test
    void testContains_size10() throws ListAccessError {
        createArray(10);
        testIfContains(array[5]);
    }

    @Test
    void testContains_sizeTwoThousand() throws ListAccessError {
        createArray(2000);
        testIfContains(array[1659]);
    }

    @Test
    void testContains_sizeLarge() throws ListAccessError {
        createArray(61238);
        testIfContains(array[1378]);
    }

    @Test
    void testTreeAndArraySizeEqual() throws ListAccessError {
        createArray(1212);
        assertEquals(linkedList.getSize(), array.length);
    }

    @Test
    void testAddElement_10() throws ListAccessError {
        createArray(20);
        addToList(10);
    }

    @Test
    void testAddElement_1000() throws ListAccessError {
        createArray(500);
        addToList(1000);
    }

    @Test
    void testAddElement_31531() throws ListAccessError {
        createArray(35);
        addToList(31531);
    }

}
