package binaryTree;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-07
 */
class BinaryTreeTest {
    @Test
    void testIfContains() {
        BinaryTree<Integer> integerTree = new BinaryTree<>();
        Integer[] integerArray = new Integer[100];
        System.out.println(integerArray.getClass());
        for(int i = 0; i < 100; i++) {
            integerArray[i] = i;
        }
        for(Integer i : integerArray) {
            integerTree.insert(i);
        }
        System.out.println(integerTree.size());
        assertEquals(integerArray.length,integerTree.size());
    }
}