package binaryTree;


import arrayGenerator.generator.ArrayGenerator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-07
 */
public abstract class BinaryTreeTest<T extends Comparable<? super T>> extends ScopedArrayGeneratorTest<T> {

    public abstract ArrayGenerator<T> getGenerator();

    private BinaryTree<T> tree;
    private T[] array;


    /**
     * Creates required size generic array AND transfers all the elements to binary tree
     *
     * @param size required size of array
     */
    public void createArray(int size) {
        if (tree != null) {
            tree = null;
        } else if (array != null) {
            array = null;
        }
        array = getGenerator().getArray(size);
        this.tree = arrayToBinaryTree((array));
    }

    /**
     * Takes all elements from given array and insert each of them to a binary tree
     *
     * @param array which elements are going to be stored in binary tree
     * @return manipulated binary tree
     */
    private BinaryTree<T> arrayToBinaryTree(T[] array) {
        BinaryTree<T> tree = new BinaryTree<T>();
        for (T value : array) tree.insert(value);
        return tree;
    }

    /**
     * Creates an required size array and then
     * Takes all elements from given array and insert each of them to a binary tree
     *
     * @param size of array to be initialized
     * @return manipulated binary tree
     */
    private BinaryTree<T> newBinaryTree(int size) {
        BinaryTree<T> tree = new BinaryTree<T>();
        for (T value : getGenerator().getArray(size)) {
            tree.insert(value);
        }
        return tree;
    }

    /**
     * Checks if given value is in binary tree
     *
     * @param value to be checked if tree contains it
     */
    private void testIfContains(T value) {
        boolean arrayContains = false;
        for (Object element : array) {
            if (element == value) {
                arrayContains = true;
                break;
            }
        }
        assertEquals(this.tree.contains(value), arrayContains);
    }

    /**
     * Adds the first ( index 0 ) element to a binary tree required amount of times
     *
     * @param size how many times to add the value
     */
    private void addToTree(int size) {
        for (int i = 0; i < size; i++) {
            tree.insert(array[0]);
        }
        assertEquals(tree.size(), array.length + size);
    }

    @Test
    void testContains_size10() {
        createArray(10);
        testIfContains(array[5]);
    }

    @Test
    void testContains_sizeTwoThousand() {
        createArray(2000);
        testIfContains(array[1659]);
    }

    @Test
    void testContains_sizeLarge() {
        createArray(61238);
        testIfContains(array[1378]);
    }

    @Test
    void testTreeAndArraySizeEqual() {
        createArray(1212);
        assertEquals(tree.size(), array.length);
    }

    @Test
    void testAddElement_10() {
        createArray(20);
        addToTree(10);
    }

    @Test
    void testAddElement_1000() {
        createArray(500);
        addToTree(1000);
    }

    @Test
    void testAddElement_31531() {
        assertThrows(StackOverflowError.class, () -> {
            createArray(35);
            addToTree(31531);
        });
    }
}