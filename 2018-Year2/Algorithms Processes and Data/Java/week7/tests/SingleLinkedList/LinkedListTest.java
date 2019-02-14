package SingleLinkedList;

import SingleLinkedList.generator.ArrayGenerator;
import SingleLinkedList.linkedList.ListAccessError;
import SingleLinkedList.linkedList.SingleLinkList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * LinkedListTest is an generic abstract class to be implemented
 * by any object which extends Comparable. IT test SingleLinkList methods
 * with any comparable object
 *
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-12-01
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
     * @throws ListAccessError if element is not found in a list
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
     * Checks if element contains value at given index
     *
     * @param index at which elemeent will be retrieved from list
     * @throws ListAccessError if index is out of bounds or element is not found
     */
    private void testIfContainsAtIndex(int index) throws ListAccessError {
        if(checkIndex(index))
            assertEquals(this.linkedList.get(index), array[index]);
    }

    private boolean checkIndex(int index) {
        if(index < 0 || index > this.linkedList.getSize()) {
            assertThrows(ListAccessError.class, () -> {
                this.linkedList.get(index);
            });
            return false;
        }
        return true;
    }

    /**
     * Removes the element at requested index from list
     * And checks if remove9) method works and binary tree size changes.
     *
     * @param index at which elemeent will be retrieved from list
     * @throws ListAccessError if index is out of bounds or element is not found
     */
    private void removeFromList(int index) throws ListAccessError {
        if(checkIndex(index)) {
            this.linkedList.remove(index);
            assertEquals(this.linkedList.getSize()+1, array.length);
        }
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
    void testContains_MinusOne() throws ListAccessError {
        createArray(10);
        testIfContainsAtIndex(-1);
    }
    @Test
    void testContains_IndexTenSizeFive() throws ListAccessError{
        createArray(5);
        testIfContainsAtIndex(10);
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
    @Test
    void testRemoveElement1_Size0() throws ListAccessError {
        createArray(0);
        removeFromList(1);
    }
    @Test
    void testRemoveElementMinuse1_Size10() throws ListAccessError {
        createArray(10);
        removeFromList(-1);
    }
    @Test
    void testRemoveElement121_Size3040() throws ListAccessError {
        createArray(3040);
        removeFromList(121);
    }
}
